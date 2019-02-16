var old_message_id = -1;
function CheckAndGetMessage() {
    $.ajax({
        type : "GET",
        contentType : "application/json",
        url: 'get_last_message',
        data: {'idOfPreviousMessage':old_message_id},
        dataType : 'json',
        success: function (result) {
            var SendMessage = "<p>  New message is: " + result.body  + "</p>" + "<p> Sender is " + result.nickName + "</p>";
            old_message_id = result.id;
            $('#MessageForCurrentUser').html(SendMessage);
            var col = document.getElementById("MessageForCurrentUser");
            col.classList.toggle("show");
        },
        error: function(){
            return;
        }

    });
};
setInterval(CheckAndGetMessage,1000);
