% 使用cvx求解
clc,clear;
n = 10;%10个间隙
a = rand(n,1);%每一个时隙的ai产生
cvx_begin
    variable xx(n,1);  
    % 目标函数：最大化每个时隙信道容量的对数之和
    maximize( sum( log(1+xx.*a)/log(2)));
    subject to  % 约束条件
        xx >= 0; % 功率分配必须大于等于0
        sum(xx) == 1;    % 功率归一化的约束
cvx_end

% 结果输出
disp('最优功率分配如下：');
disp(xx');
disp('信道容量最优值为：');
disp(cvx_optval);


disp("-------------------------------------------------------------");
disp("-------------------------------------------------------------");
% 使用注水算法求解
syms x;
n=10;                   %10个间隙
func=0.0;               % 保存目标函数值
for i=1:10
    %代入公式
    temp=1/(log(2)*x)-1/a(i);
    % 等价于func+=max(0,temp)
    func=func+0.5*(temp+abs(temp));
end

% 求解
eqn = func==1;          
lamda=double(vpasolve(eqn,x));
disp(['λ：',num2str(lamda)]);

%利用lamda求得注水线值
v=1/(log(2)*lamda);
disp(['注水线值：',num2str(v)]);


% 得到最优功率分配结果
resp=[];
for i=1:10
    %代入公式
    temp=1/(log(2)*lamda)-1/a(i);
    % 存储结果
    resp=[resp,0.5*(temp+abs(temp))];
end

% 得到信道容量最优值
ans=sum( log(1+resp'.*a)/log(2));

% 输出结果
disp('最优功率分配如下：');
disp(resp);
disp('信道容量最优值为：');
disp(ans);

% 画图
z = [];
for i = 0:n-1
 y = 1/a(i+1);      % 得到信道状态的倒数
 z = [z;i y;i+1 y]; % 保存，用于画图
end
figure(1);
% 画信道状态的倒数线
plot(z(:,1),z(:,2));
% 画注水线
line([0 n],[v v],'linestyle',':');
xlabel('i');
legend('1/a','注水线');
set(gca,'xtick',[],'ytick',[]);
% 标记注水线的值
text(-1.2,v,num2str(v));


