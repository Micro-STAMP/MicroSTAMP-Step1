var systemGoalSelected;
var systemGoalToBeDeleted;

function addSystemGoal() {
    var systemGoal = {
        name: $("#system-goal-name").val(),
        projectId: $("#project_id").val(),
    }

    $.ajax({
        url: '/system-goals',
        type: 'post',
        dataType: 'text',
        contentType: 'application/json',
        success: function (data) {
            location.reload();
        },
        data: JSON.stringify(systemGoal)
    });
}

function loadEditSystemGoal(id){
    systemGoalSelected = id;
    $.ajax({
        url: '/system-goals/'+ id,
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

    $.ajax({
        url: '/system-goals/' + systemGoalSelected,
        type: 'put',
        dataType: 'text',
        contentType: 'application/json',
        success: function (data) {
            location.reload();
        },
        data: JSON.stringify(systemGoal)
    });
}

function loadSystemGoalToBeDeleted(id){
    systemGoalToBeDeleted = id;
    $.ajax({
        url: '/system-goals/'+ id,
        type: 'get',
        success: function (data) {
             $("#system_goal_delete_name").text(data.name);
        },
    });
}

function deleteSystemGoal(){
    $.ajax({
        url: '/system-goals/'+ systemGoalToBeDeleted,
        type: 'delete',
        success: function (data) {
            location.reload();
        },
    });
}