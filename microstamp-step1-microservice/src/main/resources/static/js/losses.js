var lossSelected;
var lossToBeDeleted;

function addLoss() {
    var loss = {
        name: $("#loss-name").val(),
        projectId: $("#project_id").val(),
    }

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