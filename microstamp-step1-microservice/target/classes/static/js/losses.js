var lossSelected;
var lossToBeDeleted;

function addLoss() {
    var loss = {
        name: $("#loss-name").val(),
        projectId: $("#project_id").val(),
    }

    $('#target').html('sending..');

    if($("#loss-name").val() == ""){
        $("#addLossModal").modal("hide");
        actual_modal = 0;
        $("#namelessRestrictionModal").modal("show");
    }else{
        $.ajax({
            url: '/losses',
            type: 'post',
            dataType: 'text',
            contentType: 'application/json',
            success: function (data) {
                location.reload();
            },
            data: JSON.stringify(loss)
        });
    }
}

function loadEditLoss(id){
    lossSelected = id;
    $.ajax({
        url: '/losses/'+ id,
        type: 'get',
        success: function (data) {
            $("#loss-edit-name").val(data.name);
        },
    });
}

function editLoss() {
    var loss = {
        name: $("#loss-edit-name").val(),
    }

    $('#target').html('sending..');

    if($("#loss-edit-name").val() == ""){
        $("#editLossModal").modal("hide");
        actual_modal = 1;
        $("#namelessRestrictionModal").modal("show");
    }else{
        $.ajax({
            url: '/losses/' + lossSelected,
            type: 'put',
            dataType: 'text',
            contentType: 'application/json',
            success: function (data) {
                location.reload();
            },
            data: JSON.stringify(loss)
        });
    }
}

function loadLossToBeDeleted(id){
    lossToBeDeleted = id;
    $.ajax({
        url: '/losses/'+ id,
        type: 'get',
        success: function (data) {
             $("#loss_delete_name").text(data.name);
        },
    });
}

function deleteLoss(){
    $.ajax({
        url: '/losses/'+ lossToBeDeleted,
        type: 'delete',
        success: function (data) {
            location.reload();
        },
    });
}