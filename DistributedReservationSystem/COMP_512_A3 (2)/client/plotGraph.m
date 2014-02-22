function plotGraph()
%PLOTGRAPH Summary of this function goes here
%   Detailed explanation goes here
fid=fopen('1Rm1client');
A=fscanf(fid,'%f');
x=A(1:2:size(A));
y=A(2:2:size(A));
plot(x,y);

fid1=fopen('average_response_time_multi_rm');
A=fscanf(fid1,'%f');
x1=A(1:2:size(A));
y1=A(2:2:size(A));
plot(x,y,x1,y1);


%plot(x, [y1(:) y2(:) y3(:) y4(:)])
legend({'1 RM' '3 RMs'}, 'Location','NorthWest')
%set(gca, 'XLim',[0 1], 'YLim',[0.1 0.4])

xlabel('Transaction/sec');
ylabel('Average Response Time (ms)');
end

