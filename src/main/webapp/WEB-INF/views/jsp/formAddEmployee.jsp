<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>School library. Add Employee</title>
    <link rel="stylesheet" type="text/css" href="/styles/index.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script>
        var ctxPath = "<%=request.getContextPath() %>";
        $(document).ready(function() {
            $('#form').submit(function(e) {
                e.preventDefault();
                var formData = {"name": $("#name").val(), "surname": $("#surname").val(), "position": $("#position").val()};
                $.ajax({
                    url: ctxPath + "/library/employees",
                    type: "POST",
                    data: JSON.stringify(formData),
                    contentType: "application/json",
                    cache: false,
                    dataType: "json",
                    success: function(data, textStatus, jqXHR) {
                        alert("Employee successfully added: " + data.name + " " + data.surname + ", " + data.position);
                        window.location = ctxPath + "/library/employees";
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        if(jqXHR.status == 400) {
                            var messages = JSON.parse(jqXHR.responseText);
                            $('#messages').empty();
                            $.each(messages, function(i, v) {
                                var item = $('<li>').append(v);
                                $('#messages').append(item);
                            });
                        }
                        else {
                            alert('Server error. HTTP status: ' + jqXHR.status);
                        }
                    }
                });
            });
        });

        /*
        $(document).ready(function() {
            $("#submit").click(function(){
                var formData = {"name": $("#name").val(), "surname": $("#surname").val(), "position": $("#position").val()};
                $.ajax({
                    url: ctxPath + "/library/employees",
                    type: "POST",
                    data: formData,
                    processData: false,
//                    mimeType: "application/json",

                    // data: JSON.stringify({"name":"Michael", "surname":"Jordan", "position":"security"}),
                    // dataType: "json",
                    contentType: "application/json",
                    crossDomain: true,
                    jsonp: false,
                    success: function(data, textStatus, jqXHR) {
                        window.location.replace(ctxPath + "/library/employees");
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        if(jqXHR.status == 415) {
                            var messages = JSON.parse(jqXHR.responseText);
                            $('#messages').empty();
                            $.each(messages, function(i, v) {
                                var item = $('<li>').append(v);
                                $('#messages').append(item);
                            });
                        } else {
                            alert('Unexpected server error.');
                        }
                    }

                    success: function(data) {
                     console.log(data);
                     wait = false;
                     },
                     error : function(data) {
                     console.log("error:", data);
                     }
                });
            });
        });*/

        /*$('#form').submit(function(e) {
            // reference to form object
            var form = this;
            var formData = {"name": $("#name").val(), "surname": $("#surname").val(), "position": $("#position").val()};
            // for stopping the default action of element
            e.preventDefault();
            // map that will hold form data
            /!*var formData = {}
            //iterate over form elements
            $.each(this, function(i, v){
                var input = $(v);
                // populate form data as key-value pairs
                // with the name of input as key and its value as value
                formData[input.attr("name")] = input.val();
            });*!/
            $.ajax({
                type: form.attr('method'), // method attribute of form
                url: form.attr('action'),  // action attribute of form
                dataType : "json",
                contentType: "application/json; charset=utf-8",
                mimeType: "application/json",
                processData: false,
                cached: false,
                // convert form data to json format
                data: JSON.parse(formData),
                success: function (formData) {
                    alert(formData);
                },
                error: function (xhr) {
                    alert(xhr.responseText);
                }
            });

        });*/


        /*$.ajax({
            url:url,
            type:"POST",
            data:data,
            contentType:"application/json; charset=utf-8",
            dataType:"json",
            success: function(){
                ...
            }
        })

        jQuery["postJSON"] = function( url, data, callback ) {
            // shift arguments if data argument was omitted
            if ( jQuery.isFunction( data ) ) {
                callback = data;
                data = undefined;
            }

            return jQuery.ajax({
                url: url,
                type: "POST",
                contentType:"application/json; charset=utf-8",
                dataType: "json",
                data: data,
                success: callback
            });
        };

        $.postJSON('http://url', {data: 'goes', here: 'yey'}, function (data, status, xhr) {
            alert('Nailed it!')
        });*/

    </script>
</head>
<body>
<div class="container">
    <header>
        <h1>School library Web application</h1>
        <h3>Create new employee:</h3>
    </header>

    <nav>
        <ul>
            <li><a href="/index.jsp">Main page</a></li>
            <li><a href="/library/employees">Employees</a></li>
            <li><a href="/library/books">Books</a></li>
            <li><a href="/library/classes">Classes</a></li>
        </ul>
    </nav>

    <article>
        <div class="container">
            <%--<form id="form" class="form-horizontal" method="POST" action="/library/employees">--%>
            <form id="form" class="form-horizontal" method="POST" action="/library/employees">
                <%--<div class="form-group">
                    <div class="col-sm-2">
                        <label class="control-label" for="id">ID:</label>
                    </div>
                    <div class="col-sm-4">
                        <input class="form-control" id="id" name="id" value="0" type="text"/>
                    </div>
                </div>--%>

                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="control-label" for="name">Name:</label>
                    </div>
                    <div class="col-sm-4">
                        <input class="form-control" id="name" name="name" type="text"/>
                    </div>
                    <%--<div class="col-sm-4">
                        <label class="label-info">${employeeValidate.nameLabel}</label>
                    </div>--%>
                </div>

                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="control-label" for="surname">Surname:</label>
                    </div>
                    <div class="col-sm-4">
                        <input class="form-control" id="surname" name="surname" type="text"/>
                    </div>
                    <%--<div class="col-sm-4">
                        <label class="label-info">${employeeValidate.surnameLabel}</label>
                    </div>--%>
                </div>

                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="control-label" for="position">Position:</label>
                    </div>
                    <div class="col-sm-4">
                        <input class="form-control" id="position" name="position" type="text"/>
                        <%--<select id="position" name="position" class="form-control">
                            <option selected disabled hidden>Choose from available positions</option>
                            <option value="director">director</option>
                            <option value="deputy_director">deputy director</option>
                            <option value="librarian">librarian</option>
                            <option value="teacher">teacher</option>
                            <option value="tutor">tutor</option>
                            <option value="security">security</option>
                            <option value="cleaner">cleaner</option>
                        </select>--%>
                    </div>
                    <%--<div class="col-sm-4">
                        <label class="label-info">${employeeValidate.positionLabel}</label>
                    </div>--%>
                </div>

                <button id="submit" class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-floppy-disk"></span>Save new employee</button>
            </form>

            <form class="form-inline" action="/library/employees" method="GET">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-triangle-left"></span>Return to employees</button>
            </form>
        </div>
    </article>

    <footer>Copyright &copy; Alexey Timonov</footer>

</div>
</body>
</html>

<%--
/*//    $(document).ready(function() {
    $(function() {
        $("#submit").on('click', function() {
            $.ajax({
                cache: false,
                url: "/library/employees",
                type: "POST",
                dataType: "json",
//                accept: "application/json",
                contentType: "application/json; charset=utf-8",
//                data: $('form').serialize(),
//                data: $('#form').serialize(),
                data: JSON.stringify( { "name": $('#name').val(), "surname": $('#surname').val(), "position": $('#position').val() } ),
//                data: '{"name": $("#name").val(), "surname": $("#surname").val(), "position": $("#position").val()}'
                /!*success: function(result) {
                    console.log(result);
                },
                error: function(xhr, resp, text) {
                    console.log(xhr, resp, text);
                }*!/
            });
//            console.log(data);
        });
    });*/
--%>



