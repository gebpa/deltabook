function CheckAndGetMessage() {
    $idPrevMes = -1;
    $.ajax({
        url: 'message_for_current_user',
        success: function(data) {
            if(data == "")
                return;
            $('#MessageForCurrentUser').html(data);
            var col=document.getElementById("MessageForCurrentUser");
            col.classList.toggle("show");
            $idPrevMes =
        }
    });
};
setInterval(CheckAndGetMessage,1000);
