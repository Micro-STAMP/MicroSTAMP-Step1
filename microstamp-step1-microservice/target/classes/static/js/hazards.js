var hazardSelected;
var hazardToBeDeleted;

function addHazard() {
    var hazard = {
        name: $("#hazard-name").val(),
        projectId: $("#project_id").val(),
        lossIds: $("#hazard-losses").val(),
        fatherId: $("#hazard-father").val(),
    }

    $('#target').html('sending..');

    if($("#hazard-name").val() == ""){
        $("#addHazardModal").modal("hide");
        actual_modal = 0;
        $("#namelessRestrictionModal").modal("show");
    }else{
        $.ajax({
            url: '/hazards',
            type: 'post',
            dataType: 'text',
            contentType: 'application/json',
            success: function (data) {
                location.reload();
            },
            data: JSON.stringify(hazard)
        });
    }
}

function loadEditHazard(id){
    hazardSelected = id;
    $.ajax({
        url: '/hazards/'+ id,
        type: 'get',
        success: function (data) {
            $("#hazard-edit-name").val(data.name);
            $("#hazard-edit-losses").val(data.lossEntities);
            if(data.father == null){
                $("#hazard-edit-father").val("null");
            }else{
                $("#hazard-edit-father").val(data.father.id);
            }
        },
    });
}

function editHazard() {
    var hazard = {
        name: $("#hazard-edit-name").val(),
        lossIds: $("#hazard-edit-losses").val(),
        fatherId: $("#hazard-edit-father").val(),
    }

    $('#target').html('sending..');

    if($("#hazard-edit-name").val() == ""){
        $("#editHazardModal").modal("hide");
        actual_modal = 1;
        $("#namelessRestrictionModal").modal("show");
    }else{
        $.ajax({
            url: '/hazards/' + hazardSelected,
            type: 'put',
            dataType: 'text',
            contentType: 'application/json',
            success: function (data) {
                location.reload();
            },
            data: JSON.stringify(hazard)
        });
    }
}

function loadHazardToBeDeleted(id){
    hazardToBeDeleted = id;
    $.ajax({
        url: '/hazards/'+ id,
        type: 'get',
        success: function (data) {
             $("#hazard_delete_name").text(data.name);
        },
    });
}

function deleteHazard(){
    $.ajax({
        url: '/hazards/'+ hazardToBeDeleted,
        type: 'delete',
        success: function (data) {
            location.reload();
        },
    });
}