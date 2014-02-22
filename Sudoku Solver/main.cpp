#include <iostream>
#include <stdio.h>
#include <conio.h>
#include <map>
#include <vector>
using namespace std;

std::map<std::pair<int,int>,std::vector<int>> g_possibleValueMap;

struct SValues
{
    int times;
    std::vector<int> rowNo;
    std::vector<int> colNo;
    SValues()
    {
        times=0;
    }
};

void fillRows(int** arr,std::map<int,SValues*>& map_vals,int rowStart)
{
    for(int l=1;l<=9;l++)
    {
        if(map_vals[l]!=NULL && map_vals[l]->times==2)
        {
            int intendedRow,intendedColStart;
			intendedRow = rowStart+rowStart+1+rowStart+2 - map_vals[l]->rowNo[0] - map_vals[l]->rowNo[1];
            
			intendedColStart = (3-map_vals[l]->colNo[0]/3 - map_vals[l]->colNo[1]/3)*3;
            
			int tmpCols[3] = {0,0,0};//0 means possibilty of putting the value, 1 for value cannot be put in the col
			int tmpColCounter = 0;
			int intendedColCount = 3;
            for(int j=intendedColStart;j<intendedColStart+3;j++,tmpColCounter++)
            {
                if(arr[intendedRow][j]!=-1)
                {
					tmpCols[tmpColCounter] = 1;
					intendedColCount--;
                }
                else
                {
					for(int itr = 0;itr<9;itr++)
					{
						if(arr[itr][j]==l)
						{
							tmpCols[tmpColCounter] = 1;
							intendedColCount--;
							break;
						}
					}
                }
            }//end for loop

			//Fill the box
			if(intendedColCount==1)
			{
				for(int j=0;j<3;j++)
				{
					if(tmpCols[j]==0)
					{
						arr[intendedRow][j+intendedColStart]=l;
						//printPuzzle();
						break;
					}
				}
			}
			delete map_vals[l];
        }
    }
}

//checks rows 3 at a time
void checkRows(int** arr)
{
    std::map<int,SValues*> map_vals;
    for(int i=0;i<9;i+=3)
    {
        map_vals.clear();
        for(int j=i;j<i+3;j++)
        {
            for(int k=0;k<9;k++)
            {
                if(arr[j][k]!=-1)
                {
                    if(map_vals[arr[j][k]]==NULL)
                    {
                        SValues* tmp = new SValues();
                        tmp->rowNo.push_back(j);
                        tmp->colNo.push_back(k);
                        tmp->times++;
                        map_vals[arr[j][k]] = tmp;
                    }
                    else
                    {
                        map_vals[arr[j][k]]->times++;
                        map_vals[arr[j][k]]->rowNo.push_back(j);
                        map_vals[arr[j][k]]->colNo.push_back(k);
                    }
                }
            }
        }
        fillRows(arr,map_vals,i);

    }
}

void fillCols(int** arr,std::map<int,SValues*>& map_vals,int colStart)
{
	for(int l=1;l<=9;l++)
    {
        if(map_vals[l]!=NULL && map_vals[l]->times==2)
        {
            int intendedCol,intendedRowStart;
			intendedCol = colStart+colStart+1+colStart+2 - map_vals[l]->colNo[0] - map_vals[l]->colNo[1];
            
			intendedRowStart = (3-map_vals[l]->rowNo[0]/3 - map_vals[l]->rowNo[1]/3)*3;
            
			int tmpRows[3] = {0,0,0};//0 means possibilty of putting the value, 1 for value cannot be put in the col
			int tmpRowCounter = 0;
			int intendedRowCount = 3;
            for(int j=intendedRowStart;j<intendedRowStart+3;j++,tmpRowCounter++)
            {
                if(arr[j][intendedCol]!=-1)
                {
					tmpRows[tmpRowCounter] = 1;
					intendedRowCount--;
                }
                else
                {
					for(int itr = 0;itr<9;itr++)
					{
						if(arr[j][itr]==l)
						{
							tmpRows[tmpRowCounter] = 1;
							intendedRowCount--;
							break;
						}
					}
                }
            }//end for loop

			//Fill the box
			if(intendedRowCount==1)
			{
				for(int j=0;j<3;j++)
				{
					if(tmpRows[j]==0)
					{
						arr[j+intendedRowStart][intendedCol]=l;
						//printPuzzle();
						break;
					}
				}
			}
			delete map_vals[l];
        }
    }
}


void checkCols(int** arr)
{
	std::map<int,SValues*> map_vals;
    for(int i=0;i<9;i+=3)
    {
        map_vals.clear();
        for(int j=i;j<i+3;j++)
        {
            for(int k=0;k<9;k++)
            {
                if(arr[k][j]!=-1)
                {
                    if(map_vals[arr[k][j]]==NULL)
                    {
                        SValues* tmp = new SValues();
                        tmp->rowNo.push_back(k);
                        tmp->colNo.push_back(j);
                        tmp->times++;
                        map_vals[arr[k][j]] = tmp;
                    }
                    else
                    {
                        map_vals[arr[k][j]]->times++;
                        map_vals[arr[k][j]]->rowNo.push_back(k);
                        map_vals[arr[k][j]]->colNo.push_back(j);
                    }
                }
            }
        }
        fillCols(arr,map_vals,i);

    }
}

void calcPossibleValues(int** arr)
{
	bool nextVal=false;
	int rowStart;
	int colStart;
	for(int i=0;i<9;i++)
	{
		rowStart = (i/3)*3;
		for(int j=0;j<9;j++)
		{
			colStart = (j/3)*3;
			for(int l=1;l<=9;l++)
			{
				nextVal = false;
				if(arr[i][j]==-1)
				{
					//check col
					for(int itr=0;itr<9;itr++)
					{
						if(arr[i][itr]==l)
						{
							nextVal = true;
							break;
						}
					}
					if(nextVal)
						continue;
					//check row
					for(int itr=0;itr<9;itr++)
					{
						if(arr[itr][j]==l)
						{
							nextVal = true;
							break;
						}
					}
					if(nextVal)
						continue;
					//check 3X3
					
					for(int itr1=rowStart;itr1<rowStart+3;itr1++)
					{
						for(int itr2=colStart;itr2<colStart+3;itr2++)
						{
							if(arr[itr1][itr2]==l)
							{
								nextVal = true;
								break;
							}
						}
						if(nextVal)
							break;
					}
					if(nextVal==false)
					{
						std::pair<int,int> tmpPair(i,j);
						g_possibleValueMap[tmpPair].push_back(l);
					}
				}
			}
		}
	}
}
void fillIndividualBox(int** arr)
{
	calcPossibleValues(arr);
	for(int i =0;i<9;i++)
	{
		for(int j=0;j<9;j++)
		{
			std::pair<int,int> tmpPair(i,j);
			if(g_possibleValueMap[tmpPair].size()==1)
			{
				arr[i][j]=g_possibleValueMap[tmpPair].front();
			}
		}
	}
	g_possibleValueMap.clear();
}


void printPuzzle(int** arr)
{
    printf("\n\n");
    for(int i=0;i<9;i++)
    {
        for(int j=0;j<9;j++)
        {
            if(arr[i][j]!=-1)
                printf("|(%d)",arr[i][j]);
            else
                printf("|( )");
        }
        printf("\n");
        for(int j=0;j<9;j++)
        {
            printf("****");
        }
        printf("\n");
    }
    printf("\n\n");
	_getch();
}

int findUnfilledBoxes(int **arr)
{
	int newUnfillCounter = 0;
	for(int i=0;i<9;i++)
	{
		for(int j=0;j<9;j++)
			if(arr[i][j]==-1)
			{
				newUnfillCounter++;
			}
	}
	return newUnfillCounter;
}

int main()
{
	int** arr;
	arr = new int *[9];
    for(int i=0;i<9;i++)
    {
		arr[i] = new int[9];
        for(int j=0;j<9;j++)
            arr[i][j]=-1;
    }
    arr[0][3]=2;
    arr[0][7]=6;
    arr[0][8]=3;

    arr[1][0]=3;
    arr[1][5]=5;
    arr[1][6]=4;
    arr[1][8]=1;

    arr[2][2]=1;
    arr[2][5]=3;
    arr[2][6]=9;
    arr[2][7]=8;

    arr[3][7]=9;

    arr[4][3]=5;
    arr[4][4]=3;
    arr[4][5]=8;

    arr[5][1]=3;

    arr[6][1]=2;
    arr[6][2]=6;
    arr[6][3]=3;
    arr[6][6]=5;

    arr[7][0]=5;
    arr[7][2]=3;
    arr[7][3]=7;
    arr[7][8]=8;

    arr[8][0]=4;
    arr[8][1]=7;
    arr[8][5]=1;

    printPuzzle(arr);
	int oldUnfillCounter = 81;
	int newUnfillCounter = 0;
    while(newUnfillCounter!=oldUnfillCounter)
    {
        checkRows(arr);
        checkCols(arr);
		fillIndividualBox(arr);
		oldUnfillCounter = newUnfillCounter;
		newUnfillCounter = findUnfilledBoxes(arr);
		
    }
	
	//printf("\n\nUnfilled boxes = %d\n\n",newUnfillCounter);
    printPuzzle(arr);
    return 0;
}

