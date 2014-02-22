function plotGraph1()
%PLOTGRAPH1 Summary of this function goes here
%   Detailed explanation goes here
fid=fopen('50c10000o');
clients=50;
A=fscanf(fid,'%f');
x=A(1:2:size(A));
y=A(2:2:size(A));
a=x(1);
m=0.0;
num=0;
X=[];
Y=[];
for i=1:1:size(x)
    if(x(i)==a)
        num=num+1;
        m=m+y(i);
    else
        X=[X a*clients];
        a=x(i);
        m=m/num;
        Y=[Y m];
        m=0;
        num=0;
        i=i-1;
    end
    
    
end
X=[X a*clients];
m=m/num;
Y=[Y m];
plot(X,Y);
xlabel('Transaction/sec');
ylabel('Average Response Time (ms)');

end

