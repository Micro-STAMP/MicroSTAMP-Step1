var assumptionSelected;
var assumptionToBeDeleted;

function addAssumption() {
    var assumption = {
        name: $("#assumption-name").val(),
        projectId: $("#project_id").val(),
    }

    $('#target').html('sending..');

    if($("#assumption-name").val() == ""){
        $("#addAssumptionModal").modal("hide");
        actual_modal = 0;
        $("#namelessRestrictionModal").modal("show");
    }else{
        $.ajax({
            url: '/assumptions',
            type: 'post',
            dataType: 'text',
            contentType: 'application/json',
            success: function (data) {
                location.reload();
            },
            data: JSON.stringify(assumption)
        });
    }
}

function loadEditAssumption(id){
    assumptionSelected = id;
    $.ajax({
        url: '/assumptions/'+ id,
        type: 'get',
        success: function (data) {
            $("#assumption-edit-name").val(data.name);
        },
    });
}

function editAssumption() {
    var assumption = {
        name: $("#assumption-edit-name").val(),
    }

    $('#target').html('sending..');

    if($("#assumption-edit-name").val() == ""){
        $("#editAssumptionModal").modal("hide");
        actual_modal = 1;
        $("#namelessRestrictionModal").modal("show");
    }else{
        $.ajax({
            url: '/assumptions/' + assumptionSelected,
            type: 'put',
            dataType: 'text',
            contentType: 'application/json',
            success: function (data) {
                location.reload();
            },
            data: JSON.stringify(assumption)
        });
    }
}

function loadAssumptionToBeDeleted(id){
    assumptionToBeDeleted = id;
    $.ajax({
        url: '/assumptions/'+ id,
        type: 'get',
        success: function (data) {
             $("#assumption_delete_name").text(data.name);
        },
    });
}

function deleteAssumption(){
    $.ajax({
        url: '/assumptions/'+ assumptionToBeDeleted,
        type: 'delete',
        success: function (data) {
            location.reload();
        },
    });
}