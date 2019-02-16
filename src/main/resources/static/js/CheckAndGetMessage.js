function CheckAndGetMessage() {
    $.ajax({
        type : "GET",
        contentType : "application/json",
        url: 'message_for_current_user',
        dataType : 'json',
        success: function(result) {
            $.each(result.data, function(message){
                var message = "<p>  New message is" + ", message.getBody() " +"</p>" + "<p> Sender is " + message.getSenderID().getLogin() + "</p>";
                $('#popup.popuptext').append(message)
            });
            var col=document.getElementById("MessageForCurrentUser");
            col.classList.toggle("show");
        }
    });
};
setInterval(CheckAndGetMessage,1000);
