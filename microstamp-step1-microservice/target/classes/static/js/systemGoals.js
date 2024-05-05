var systemGoalSelected;
var systemGoalToBeDeleted;

function addSystemGoal() {
    var systemGoal = {
        name: $("#system-goal-name").val(),
        projectId: $("#project_id").val(),
    }

    $('#target').html('sending..');

    if($("#system-goal-name").val() == ""){
        $("#addSystemGoalModal").modal("hide");
        actual_modal = 0;
        $("#namelessRestrictionModal").modal("show");
    }else{
        $.ajax({
            url: '/systemgoals',
            type: 'post',
            dataType: 'text',
            contentType: 'application/json',
            success: function (data) {
                location.reload();
            },
            data: JSON.stringify(systemGoal)
        });
    }
}

function loadEditSystemGoal(id){
    systemGoalSelected = id;
    $.ajax({
        url: '/systemgoals/'+ id,
        type: 'get',
        success: function (data) {
            $("#system-goal-edit-name").val(data.name);
        },
    });
}

function editSystemGoal() {
    var systemGoal = {
        name: $("#system-goal-edit-name").val(),
    }

    $('#target').html('sending..');

    if($("#system-goal-edit-name").val() == ""){
        $("#editSystemGoalModal").modal("hide");
        actual_modal = 1;
        $("#namelessRestrictionModal").modal("show");
    }else{
        $.ajax({
            url: '/systemgoals/' + systemGoalSelected,
            type: 'put',
            dataType: 'text',
            contentType: 'application/json',
            success: function (data) {
                location.reload();
            },
            data: JSON.stringify(systemGoal)
        });
    }
}

function loadSystemGoalToBeDeleted(id){
    systemGoalToBeDeleted = id;
    $.ajax({
        url: '/systemgoals/'+ id,
        type: 'get',
        success: function (data) {
             $("#system_goal_delete_name").text(data.name);
        },
    });
}

function deleteSystemGoal(){
    $.ajax({
        url: '/systemgoals/'+ systemGoalToBeDeleted,
        type: 'delete',
        success: function (data) {
            location.reload();
        },
    });
}