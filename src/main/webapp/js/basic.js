function isLogged() {
    $.ajax({
        url:'isLogged.do',
        type:'get',
        dataType:"json",
        success:function (responseText,statusText) {
            if(responseText.success==true){
                $("div.login-state").html("<a class='navbar-text login-or-person' href='person-center.do'>"+responseText.data.username+"</a><a class='navbar-text register-or-logout' href='logout.do'>注销</a>");
                $("span.span-log-status").html("<a href='person-center.do'>"+responseText.data.username+"</a><a href='logout.do'>注销</a>");
            }
        },
        error:function (XMLHttpRequest,textStatus) {

        }
    });
}