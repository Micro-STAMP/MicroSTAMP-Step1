var systemSafetyConstraintSelected;
var systemSafetyConstraintToBeDeleted;

function addSystemSafetyConstraint() {
    var systemSafetyConstraint = {
        name: $("#system-safety-constraint-name").val(),
        projectId: $("#project_id").val(),
        hazardsId: $("#system-safety-constraint-hazards").val(),
    }

    $.ajax({
        url: '/system-safety-constraints',
        type: 'post',
        dataType: 'text',
        contentType: 'application/json',
        success: function (data) {
            location.reload();
        },
        data: JSON.stringify(systemSafetyConstraint)
    });
}

function loadEditSystemSafetyConstraint(id){
    systemSafetyConstraintSelected = id;
    $.ajax({
        url: '/system-safety-constraints/'+ id,
        type: 'get',
        success: function (data) {
            $("#system-safety-constraint-edit-name").val(data.name);
        },
    });
}

function editSystemSafetyConstraint() {
    var systemSafetyConstraint = {
        name: $("#system-safety-constraint-edit-name").val(),
        hazardsId: $("#system-safety-constraint-edit-hazards").val(),
    }

    $.ajax({
        url: '/system-safety-constraints/' + systemSafetyConstraintSelected,
        type: 'put',
        dataType: 'text',
        contentType: 'application/json',
        success: function (data) {
            location.reload();
        },
        data: JSON.stringify(systemSafetyConstraint)
    });
}

function loadSystemSafetyConstraintToBeDeleted(id){
    systemSafetyConstraintToBeDeleted = id;
    $.ajax({
        url: '/system-safety-constraints/'+ id,
        type: 'get',
        success: function (data) {
             $("#system_safety_constraint_delete_name").text(data.name);
        },
    });
}

function deleteSystemSafetyConstraint(){
    $.ajax({
        url: '/system-safety-constraints/'+ systemSafetyConstraintToBeDeleted,
        type: 'delete',
        success: function (data) {
            location.reload();
        },
    });
}

