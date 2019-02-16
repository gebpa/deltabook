function CheckAndGetMessage() {
    $.ajax({
        type : "GET",
        contentType : "application/json",
        url: 'message_for_current_user',
        data: {'idOfPreviousMessage': '-1'},
        dataType : 'json',
        success: function (result) {
        var SendMessage = "<p>  New message is" + result.body  + "</p>" + "<p> Sender is " + result.nickanme + "</p>";
            $('#MessageForCurrentUser').html(SendMessage);

            var col = document.getElementById("MessageForCurrentUser");
            col.classList.toggle("show");
            idOfPreviousMessage = result.id;

        },
        error: function(){
            alert('error!');
        }

    });
};
setInterval(CheckAndGetMessage,1000);
