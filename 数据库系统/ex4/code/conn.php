<?php
$hostname = "localhost"; //主机名,可以用IP代替
$database = "ex4"; //数据库名
$username = "root"; //数据库用户名
$password = "Lry2021220003"; //数据库密码
$conn = new mysqli(
    $hostname,
    $username,
    $password,
    $database
);

if ($conn->connect_error) {
    die("数据库连接失败" . $conn->connect_error);
}
