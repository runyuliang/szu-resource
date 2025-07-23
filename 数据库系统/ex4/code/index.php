<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>租车系统</title>

    <style>
    body {
        font-family: 'Arial', sans-serif;
        color: aliceblue;
        margin: 0;
        padding: 0;
        background: url('background.png');
        background-size: cover;
    }

    header {
        background-color: #3F3F3F;
        /*深灰 */
        color: white;
        text-align: center;
        padding: 1em;
    }

    nav {
        background-color: #6b5b95;
        /* 紫色 */
        color: white;
        text-align: center;
        padding: 1em;
    }

    nav a {
        color: white;
        text-decoration: none;
        margin: 0 10px;
        transition: all 0.3s ease;
        /* 添加过渡效果 */
    }

    nav a:hover {
        text-decoration: underline;
        transform: scale(1.2);
        /* 鼠标悬停时放大 */
        background-color: rgba(107, 91, 149, 0.8);
        /* 半透明背景颜色 */
    }

    section {
        padding: 20px;
    }

    footer {
        background-color: #3F3F3F;
        /*深灰 */
        color: white;
        text-align: center;
        padding: 1em;
        position: absolute;
        bottom: 0;
        width: 100%;
    }
    </style>
</head>

<body>

    <header>
        <h1>欢迎来到租车系统</h1>
    </header>

    <nav>
        <a href="car_list.php">可用车辆</a>
        <a href="car_query.php">查询车辆</a>
        <a href="student_violation.php">学生客户违章情况</a>
        <a href="popular_places.php">热门地点</a>
        <a href="rental_trends.php">租车趋势</a>
        <a href="price_increase.php">涨价</a>

        <a href="income_statement.php">收入报表</a>
    </nav>

    <section>
        <?php
        // 获取当前小时
        date_default_timezone_set('Asia/Shanghai'); // 设置时区为上海
        $hours = date('H');

        // 根据时间输出不同的问候语
        if ($hours >= 5 && $hours < 12) {
            $greeting = '管理员，早上好！';
        } else if ($hours >= 12 && $hours < 18) {
            $greeting = '管理员，下午好！';
        } else if ($hours >= 18 && $hours < 24) {
            $greeting = '管理员，晚上好！';
        } else {
            $greeting = '管理员，夜深了，注意休息！';
        }

        // 在页面上显示问候语
        echo '<h2>' . $greeting . '</h2>';
        ?>
    </section>

    <section>
        <h2>本系统能做什么</h2>
        <ul>
            <li>查看可用车辆</li>
            <li>查询符合条件的车辆</li>
            <li>分析最受欢迎的交易地点</li>
            <li>找出有违章经历的学生客户</li>
            <li>分析租车趋势</li>
            <li>一键涨价</li>
            <li>本月财务报表</li>
        </ul>
    </section>

    <footer>
        <p>&copy; Made by Liang Runyu, 2023.</p>
    </footer>

</body>

</html>