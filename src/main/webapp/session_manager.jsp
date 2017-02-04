<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Session Manager</title>
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        function query() {
            var key = $('#key').val();
            document.location='session/'+key
        }
    </script>
</head>
<body>
<form action="session" method="post">
    key:<input id="key" name="key" /><input type="button" value="查询" onclick="query()" /><br />
    value:<input name="value" />
    <input type="submit" value="提交"/>
</form>
</body>

</html>
