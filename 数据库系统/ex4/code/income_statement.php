<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>收入报表</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>

    <header>
        <h2>收入报表</h2>
    </header>

    <nav>
        <!-- ... 省略导航链接 ... -->
    </nav>

    <section class="report-section">
        <div class="report-card">
            <label for="startDate">起始时间：</label>
            <input type="date" id="startDate" name="startDate" value="<?php echo date('Y-m-01'); ?>">

            <label for="endDate">结束时间：</label>
            <input type="date" id="endDate" name="endDate" value="<?php echo date('Y-m-d'); ?>">

            <button onclick="generateIncomeStatement()">生成报表</button>
        </div>
    </section>
    <?php
    // 在此处添加报表生成和展示的 PHP 代码
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        // 获取前端传递的起始时间和结束时间参数
        $startDate = $_POST["startDate"];
        $endDate = $_POST["endDate"];

        // 连接到数据库
        include("conn.php");

        // 根据起始时间和结束时间从 Rentals 表中查询租车记录，并联接 Car 表获取价格信息
        $sql = "SELECT r.*, c.Make, c.Model, c.NumberOfPassengers, c.Description, c.PricePerHour, c.PricePerDay FROM Rentals r JOIN Car c ON r.CarID = c.CarID WHERE DATE(r.PickupDate) >= '$startDate' AND DATE(r.DropDate) <= '$endDate'";
        $result = $conn->query($sql);

        // 输出表格头部
        echo "<table border='1'>";
        echo "<tr><th>RentalID</th><th>CustomerID</th><th>CarID</th><th>PickupDate</th><th>DropDate</th><th>LocationId</th><th>Make</th><th>Model</th><th>PricePerHour</th><th>PricePerDay</th><th>Revenue</th></tr>";

        // 处理查询结果，计算报表数据
        $totalRevenue = 0;

        while ($row = $result->fetch_assoc()) {
            // 计算租车时长
            $pickupDate = new DateTime($row["PickupDate"]);
            $dropDate = new DateTime($row["DropDate"]);
            $rentalDuration = $pickupDate->diff($dropDate);
            // 根据租车时长和价格计算收入
            $pricePerHour = $row["PricePerHour"];
            $pricePerDay = $row["PricePerDay"];
            $revenue = $rentalDuration->h * $pricePerHour + $rentalDuration->d  * $pricePerDay;
            $totalRevenue += $revenue;

            // 输出每一行租车数据
            echo "<tr>";
            echo "<td>" . $row["RentalID"] . "</td>";
            echo "<td>" . $row["CustomerID"] . "</td>";
            echo "<td>" . $row["CarID"] . "</td>";
            echo "<td>" . $row["PickupDate"] . "</td>";
            echo "<td>" . $row["DropDate"] . "</td>";
            echo "<td>" . $row["LocationId"] . "</td>";
            echo "<td>" . $row["Make"] . "</td>";
            echo "<td>" . $row["Model"] . "</td>";
            echo "<td>" . $row["PricePerHour"] . "</td>";
            echo "<td>" . $row["PricePerDay"] . "</td>";
            echo "<td>" . $revenue . "</td>";
            echo "</tr>";
        }

        // 输出总收益
        echo "<tr><td colspan='10'>总收益</td><td>" . $totalRevenue . "</td></tr>";

        // 输出表格尾部
        echo "</table>";

        // 关闭数据库连接
        $conn->close();
    }
    ?>
    <section class="report-results">
        <div id="incomeStatementResults"></div>
    </section>
    <section class="home-button">
        <div class="home-card">
            <a href="index.php">返回首页</a>
        </div>
    </section>
    <footer>
        <p>&copy; Made by Liang Runyu, 2023.</p>
    </footer>

    <script>
        function generateIncomeStatement() {
            var startDate = document.getElementById("startDate").value;
            var endDate = document.getElementById("endDate").value;

            // 使用 JavaScript 发送 AJAX 请求
            var xhr = new XMLHttpRequest();

            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    // 请求成功时的处理
                    // 在这里处理后端返回的报表数据，可以将数据显示在页面上或进行其他操作
                    document.getElementById("incomeStatementResults").innerHTML = xhr.responseText;
                }
            };

            // 发送 POST 请求到当前 PHP 脚本，传递起始时间和结束时间参数
            xhr.open("POST", "income_statement.php", true);
            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhr.send("startDate=" + startDate + "&endDate=" + endDate);
        }
    </script>

</body>

</html>