%Author: Navjot Singh
%Institution: McGill University
%Date: 16th December 2013
%Email: navjot.singh2@mail.mcgill.ca, navjot.tu@gmail.com

%Requirements:
%A sift algorithm which provides k-by-128 descriptors. Currently using sift algorithm given at http://www.cs.ubc.ca/~lowe/keypoints  SIFT demo program (Version 4, July 2005)
%Input:
%This function takes a dataset directory path and finds all its sub-directories.
%For each of these sub-directories, it finds all the files and calculate sift descriptors.
%These sift descriptors are stored in a K-by-129 matrix. The last column of matrix has an index identifying to which directory/logoname it belongs to.
%It builds a k-dimensional tree structure from these descriptors.It tries to make the tree as balanced as possible.
%It asks for a query image path and how to search(linear or using kd tree) and gives the result.
%k-d tree approach uses best bin for finding nearest neighbour and we can specify the bin sizes.
%The searching algorithm uses a patch based approach. A patch is selected and all the descriptors within that patch are compared to the exsisting descriptors.
%A patch having maximum nearest neighbours for a logo is selected as the winner.
%Note: The accuracy of algorithm depends on the dataset, patch size and the type of searching algorithm used.

function [Tree F]=findLogo(dirpath)
format LONGE;
Names={};
F=[];
Tree=int32([]);
D=dir(dirpath);
for idx=1:size(D,1)
    if(D(idx).isdir==1 && ~strcmp(D(idx).name,'.') && ~strcmp(D(idx).name,'..'))
        Names=[Names D(idx).name];
    end
end
display(Names);
for i=1:size(Names,2)
    dirpath1=strcat(dirpath,'/',Names{i});
    display(dirpath1);
    D=dir(dirpath1);
    for idx=1:size(D,1)
        if(D(idx).isdir==0)
            logoname=strcat(dirpath1,'/',D(idx).name);
            display(strcat('Calculating sift for:->',logoname));
            [img1, descr1, locs] = sift(logoname);
            col1=zeros(size(descr1,1),1);
            col1(:)=i;
            F=[F;[descr1 col1]];
            clear logo;
        end
    end
end

%%build tree
idx_divide=1;
A=F(find(F(:,idx_divide)>-11),idx_divide);
I=find(A<=median(A));%%indices
B=A(I);%%B has values
[m i]=max(B);
index1=I(i);%%index is row

%selected as root
Tree=[Tree;[index1 -11 -11]];


F_indices=find(F(:,1)>-11);
F_indices(index1)=-11;
parsed=0;
while(true)
    I=find(F_indices(:,1)>-11);
    if(size(I,1)==0)
        break;
    else
        display(strcat('Tree size: ',num2str(size(Tree,1))));
        display(strcat('Nodes left are: ',num2str(size(I,1))));
    end
    tree_rows=size(Tree,1);
    while(parsed<tree_rows)
        
        currIndex=parsed+1;
        row_of_val=Tree(currIndex,1);
        if(row_of_val>-11)
            val=F(row_of_val,idx_divide);
        else
            val=-11;
        end
        if(val==-11)
            parsed=parsed+1;
            continue;
        end
        nextDim=idx_divide+1;
        if(nextDim==129)
            nextDim=1;
        end
        
        %%Choosing correct Indices to insert
        itr1=idx_divide-1;
        if(itr1==0)
            itr1=128;
        end
        
        Indices=find(F_indices(:,1)>-11);
        
        
        
        rightchild=1;
        IndxTemp=find(Tree(:,2)==currIndex);
        if(size(IndxTemp,1)==0)
            rightchild=1;
            IndxTemp=find(Tree(:,3)==currIndex);
        else
            rightchild=0;
        end
        if(size(IndxTemp,1)==0)
            itr2=0;
        else
            itr2=IndxTemp(1);
        end
        
        while(itr2>=1)
            A1=[F(Indices,itr1) Indices(:)];
            row_of_val=Tree(itr2,1);
            val=F(row_of_val,itr1);
            if(rightchild==0)
                I=find(A1(:,1)<=val);
            else
                I=find(A1(:,1)>val);
            end
            Indices=A1(I,2);
            IndxTemp=find(Tree(:,2)==itr2);
            if(size(IndxTemp,1)==0)
                rightchild=1;
                IndxTemp=find(Tree(:,3)==itr2);
            else
                rightchild=0;
            end
            if(size(IndxTemp,1)==0)
                itr2=0;
            else
                itr2=IndxTemp(1);
            end
            itr1=itr1-1;
            if(itr1==0)
                itr1=128;
            end
        end
        %%
        
        A1=[F(Indices,idx_divide) Indices(:)];
        row_of_val=Tree(currIndex,1);
        val=F(row_of_val,idx_divide);
        %find indices I of all rows having values less than tree node on idx_divide dimension.
        I=find(A1(:,1)<=val);
        if(size(I,1)>0)
            B11=A1(I,:);
            A2=[F(B11(:,2),nextDim) B11(:,2)];
            I1=find(A2(:,1)<=median(A2(:,1)));
            B=A2(I1,:);%%B has values
            [m i]=max(B(:,1));
            index1=B(i,2);%%index is row
            Tree=[Tree;[index1 -11 -11]];
            Tree(currIndex,2)=size(Tree,1);
            
            F_indices(index1,:)=-11;
        else
            %display('!!!!!!!!!!!!!!!!!!No value found');
        end
        
        
        I=find(A1(:,1)>val);
        if(size(I,1)>0)
            B12=A1(I,:);
            A2=[F(B12(:,2),nextDim) B12(:,2)];
            I1=find(A2(:,1)<=median(A2(:,1)));
            B=A2(I1,:);%%B has values
            [m i]=max(B(:,1));
            index2=B(i,2);%%index is row
            Tree=[Tree;[index2 -11 -11]];
            Tree(currIndex,3)=size(Tree,1);
            F_indices(index2,:)=-11;
        else
            %display('!!!!!!!!!!!!!!!!!!No value found');
        end
        
        if(index1==index2)
            display('!!!!!!!!!!!!!!!!!!Same descriptor inserted twice');
        end
        
        parsed=parsed+1;
    end
    idx_divide=idx_divide+1;
    if(idx_divide==129)
        idx_divide=1;
    end
end

clear F_indices;
clear A1;
clear A2;
clear B;
clear B11;
clear B12;
clear Indices;
if(size(F,1)~=size(Tree,1))
    display('!!!!!!!!!!!!!!!!!!!!!!Size mismatch between Tree and data');
    return;
end
Root=F(Tree(1),1:end-1);
veryHighNum=double(100.0);

while(true)
    NumOccurences=zeros(1,size(Names,2));
    img_path = input('Enter image path or q to quit>>','s');
    if(img_path=='q')
        break;
    end
    use_tree = input('Use k-d tree structure? 1 for yes >>');
    bin_size=1;
    if(use_tree==1)
        bin_size = input('Enter Bin Size>>');
        display('Using k-d tree approach might not give accurate results.')
    end
    binMatrix=ones(bin_size,3).*veryHighNum;%1st col=euclidean distance 2nd col=childTreeIndex to parse 3rd col=Child_idx_divide
    
    [img1, all_descr, all_locs] = sift(img_path);
    
    patch_size = input('Enter patch Size >> ');
    increment_patch_size = input('Enter increment value for patch >> ');
    %patch_size=90;
    %increment_patch_size=patch_size/10;
    display('Please wait. Comparing descriptors');
    
    Img_matrix=rgb2gray(imread(img_path));
    [row_Img_matrix col_Img_matrix]=size(Img_matrix);
    Indices=find(all_locs(:,1)>=1);
    locs=[all_locs(:,:) Indices(:)];
    overall_hi_percent=double(0);
    total_num_desc=size(all_descr,1);
    for row_itr=1:increment_patch_size:row_Img_matrix-patch_size
        for col_itr=1:increment_patch_size:col_Img_matrix-patch_size
            %search in all_descr for rows and cols
            
            Indices=find(locs(:,1)>=row_itr);
            locs_temp=locs(Indices(:),:);
            Indices=find(locs_temp(:,1)<row_itr+patch_size);
            Indices=locs_temp(Indices(:),5);
            locs_temp=locs(Indices(:),:);
            Indices=find(locs_temp(:,2)>=col_itr);
            Indices=locs_temp(Indices(:),5);
            locs_temp=locs(Indices(:),:);
            Indices=find(locs_temp(:,2)<col_itr+patch_size);
            Indices=locs_temp(Indices(:),5);
            
            descr1=all_descr(Indices(:),:);
            num_descrp=size(descr1,1);
            
            for itr=1:num_descrp
                
                desc_row=descr1(itr,:);
                if(use_tree==1)
                    tree_index=1;
                    idx_divide=1;
                    least_dist=double(norm(Root-desc_row));
                    second_least_dist=least_dist;
                    least_dis_row_index=Tree(tree_index);
                    binMatrix=double(ones(bin_size,3,'double').*veryHighNum);
                    
                    if(Root(idx_divide)>desc_row(idx_divide))
                        binMatrix=[[least_dist Tree(tree_index,2) idx_divide+1];binMatrix];
                        binMatrix=binMatrix(1:end-1,:);
                        tree_index=Tree(tree_index,3);
                    else
                        
                        binMatrix=[[least_dist Tree(tree_index,3) idx_divide+1];binMatrix];
                        binMatrix=binMatrix(1:end-1,:);
                        tree_index=Tree(tree_index,2);
                    end
                    Node=F(Tree(tree_index,1),1:end-1);
                    idx_divide=idx_divide+1;
                    while(true)
                        dist1=norm(Node-desc_row);
                        
                        if(dist1<least_dist)
                            second_least_dist=least_dist;
                            least_dist=dist1;
                            least_dis_row_index=Tree(tree_index,1);
                            child_idx_divide=idx_divide+1;
                            if(child_idx_divide==129)
                                child_idx_divide=1;
                            end
                            if(Node(idx_divide)>desc_row(idx_divide))
                                binMatrix=[[least_dist Tree(tree_index,2) child_idx_divide];binMatrix];
                            else
                                binMatrix=[[least_dist Tree(tree_index,3) child_idx_divide];binMatrix];
                            end
                            [max_dist index_max_dist]=max(binMatrix(:,1));
                            
                            binMatrix=binMatrix([1:index_max_dist-1,index_max_dist+1:end],:);
                            
                        else
                            [max_dist index_max_dist]=max(binMatrix(:,1));
                            if(max_dist>dist1)
                                child_idx_divide=idx_divide+1;
                                if(child_idx_divide==129)
                                    child_idx_divide=1;
                                end
                                binMatrix=binMatrix([1:index_max_dist-1,index_max_dist+1:end],:);
                                if(Node(idx_divide)>desc_row(idx_divide))
                                    binMatrix=[[dist1 Tree(tree_index,2) child_idx_divide];binMatrix];
                                else
                                    binMatrix=[[dist1 Tree(tree_index,3) child_idx_divide];binMatrix];
                                end
                                
                            end
                        end
                        if(Node(idx_divide)>desc_row(idx_divide))
                            tree_index=Tree(tree_index,3);
                        else
                            tree_index=Tree(tree_index,2);
                        end
                        
                        idx_divide=idx_divide+1;
                        if(idx_divide==129)
                            idx_divide=1;
                        end
                        if(tree_index>0 && tree_index<size(Tree,1) && Tree(tree_index)~=-11)
                            Node=F(Tree(tree_index,1),1:end-1);
                        else
                            
                            Indices=find(binMatrix(:,1)<veryHighNum);
                            Indices=find(binMatrix(Indices(:,1),2)>-11);
                            if(size(Indices,1)==0)
                                break;
                            end
                            indTemp=[binMatrix(Indices(:),:) Indices(:)];
                            [minVal min_indx]=min(indTemp(:,1));
                            min_indx=indTemp(min_indx,4);
                            tree_index=binMatrix(min_indx,2);
                            idx_divide=binMatrix(min_indx,3);
                            binMatrix(min_indx,:)=double([veryHighNum veryHighNum veryHighNum]);
                            
                            if(tree_index==veryHighNum)% || tree_index==-11)
                                break;
                            end
                        end
                    end
                    if(least_dist/second_least_dist<0.8)
                        idx=uint16(F(least_dis_row_index,129));
                        NumOccurences(idx)=NumOccurences(idx)+1;
                    end
                    
                else %linear search
                    least_dist=norm(F(1,1:end-1)-desc_row);
                    second_least_dist=least_dist;
                    least_dis_row_index=1;
                    for itr1=2:size(F,1)
                        dist1=norm(F(itr1,1:end-1)-desc_row);
                        if(dist1<least_dist)
                            second_least_dist=least_dist;
                            least_dist=dist1;
                            least_dis_row_index=itr1;
                        end
                    end
                    
                    if(least_dist/second_least_dist<0.8)
                        idx=uint16(F(least_dis_row_index,129));
                        NumOccurences(idx)=NumOccurences(idx)+1;
                    else
                        %display('least_dist/second_least_dist is greater than 0.8');
                    end
                end
                
                
            end
            
            
            
            %%find idx having maximum presence
            [val idx]=max(NumOccurences);
            temp_NumOccurences=NumOccurences;
            temp_NumOccurences(idx)=-Inf;
            [temp_val temp_idx]=max(temp_NumOccurences);
            temp_ratio=temp_NumOccurences(temp_idx)/NumOccurences(idx);
            if(temp_ratio>0.6)
                %display(strcat('Ratio of NumOccurences is greater than 0.6'));
                NumOccurences=zeros(1,size(Names,2));
                continue;
            end
            if(num_descrp>0)
                hi_percent=(NumOccurences(idx)/total_num_desc)*100;
            else
                hi_percent=0;
            end
            NumOccurences=zeros(1,size(Names,2));
            if(hi_percent>overall_hi_percent)
                overall_hi_percent=hi_percent;
                identified_patch=[row_itr col_itr patch_size];
                winner_str=Names(idx);
                display('Highest match till now is ');
                display(strcat(winner_str,' with percentage: ',num2str(overall_hi_percent),' at pixel with X=',num2str(identified_patch(2)),' Y=',num2str(identified_patch(1))));
            end
            
        end%end for loops for col locations
    end%end for loops for row locations
    display('Image has logo of ');
    display(strcat(winner_str,' with percentage: ',num2str(overall_hi_percent),' at pixel with X=',num2str(identified_patch(2)),' Y=',num2str(identified_patch(1)),' width and height=',num2str(identified_patch(3))));
end%end while loop

end
