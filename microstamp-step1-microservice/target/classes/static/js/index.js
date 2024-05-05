var actual_modal; // 0 -> addProject 1 -> editProject //control the restrictions return

function returnNamelessRestriction(){
    $("#namelessRestrictionModal").modal("hide");
    if(actual_modal == 0)
        $("#addSystemGoalModal").modal("show");
    else
        $("#editSystemGoalModal").modal("show");
}

$(window).ready(function () {
    var project_id = $("#project_id").val();

    $.ajax({
        "type": 'get',
        "url": '/systemgoals/findByProjectId/' + project_id,
        "dataType": "json",
        "success": function (data) {
            $.each(data, function (idx, obj) {
                $("#systemGoalsTable").append("<tr data-tt-id=\"" + obj.id + "\" data-tt-parent-id=\"" + obj.father + "\"><td>" + obj.name + "</td><td>" + obj.id + "</td><td><button style='cursor: pointer; border-radius: 5px;' data-toggle='modal' data-target='#editSystemGoalModal' onclick = loadEditSystemGoal(this.id) type='button' id=\"" + obj.id + "\"><span class='fa fa-pencil' aria-hidden='true'></span></button><button style='cursor: pointer; border-radius: 5px;' data-toggle='modal' data-target='#confirmSystemGoalDeleteModal' type='button' id=\"" + obj.id + "\" onclick = loadSystemGoalToBeDeleted(this.id)><span class='fa fa-trash'></span></button></td></tr>");
            });
            $("#systemGoalsTable").treetable({
                expandable: true,
                initialState: "expanded",
                clickableNodeNames: true,
                indent: 30
            });
        }
    });

    $.ajax({
        "type": 'get',
        "url": '/assumptions/findByProjectId/' + project_id,
        "dataType": "json",
        "success": function (data) {
            $.each(data, function (idx, obj) {
                $("#assumptionsTable").append("<tr data-tt-id=\"" + obj.id + "\" data-tt-parent-id=\"" + obj.father + "\"><td>" + obj.name + "</td><td>" + obj.id + "</td><td><button style='cursor: pointer; border-radius: 5px;' data-toggle='modal' data-target='#editAssumptionModal' onclick = loadEditAssumption(this.id) type='button' id=\"" + obj.id + "\"><span class='fa fa-pencil' aria-hidden='true'></span></button><button style='cursor: pointer; border-radius: 5px;' data-toggle='modal' data-target='#confirmAssumptionDeleteModal' type='button' id=\"" + obj.id + "\" onclick = loadAssumptionToBeDeleted(this.id)><span class='fa fa-trash'></span></button></td></tr>");
            });
            $("#assumptionsTable").treetable({
                expandable: true,
                initialState: "expanded",
                clickableNodeNames: true,
                indent: 30
            });
        }
    });

    $.ajax({
        "type": 'get',
        "url": '/losses/findByProjectId/' + project_id,
        "dataType": "json",
        "success": function (data) {
            $.each(data, function (idx, obj) {
                $("#lossesTable").append("<tr data-tt-id=\"" + obj.id + "\" data-tt-parent-id=\"" + obj.father + "\"><td>" + obj.name + "</td><td>" + obj.id + "</td><td><button style='cursor: pointer; border-radius: 5px;' data-toggle='modal' data-target='#editLossModal' onclick = loadEditLoss(this.id) type='button' id=\"" + obj.id + "\"><span class='fa fa-pencil' aria-hidden='true'></span></button><button style='cursor: pointer; border-radius: 5px;' data-toggle='modal' data-target='#confirmLossDeleteModal' type='button' id=\"" + obj.id + "\" onclick = loadLossToBeDeleted(this.id)><span class='fa fa-trash'></span></button></td></tr>");
            });
            $("#lossesTable").treetable({
                expandable: true,
                initialState: "expanded",
                clickableNodeNames: true,
                indent: 30
            });
        }
    });

   $.ajax({
        "type": 'get',
        "url": '/hazards/findByProjectId/' + project_id,
        "dataType": "json",
        "success": function (data) {
            var backup = data;
            $.each(data, function (idx, obj) {
                if(obj.father == null){
                    $("#hazardsTable").append("<tr data-tt-id=\"" + obj.id + "\" data-tt-parent-id=\"" + obj.father + "\"><td>" + obj.name + "</td><td>" + obj.id + "</td><td><button style='cursor: pointer; border-radius: 5px;' data-toggle='modal' data-target='#editHazardModal' onclick = loadEditHazard(this.id) type='button' id=\"" + obj.id + "\"><span class='fa fa-pencil' aria-hidden='true'></span></button><button style='cursor: pointer; border-radius: 5px;' data-toggle='modal' data-target='#confirmHazardDeleteModal' type='button' id=\"" + obj.id + "\" onclick = loadHazardToBeDeleted(this.id)><span class='fa fa-trash'></span></button></td></tr>");
                    if(obj.lossEntities.length > 0){
                        $("#hazardsTable").append("<tr data-tt-id=\"" + obj.id + "-l" + "\" data-tt-parent-id=\"" + obj.id + "\"><td>" + "Losses Associated" + "</td><td>" + "</td><td></td></tr>");
                        $.each(obj.lossEntities, function (idx, loss) {
                            $("#hazardsTable").append("<tr data-tt-id=\"" + obj.id + "-l-" + loss.id + "\" data-tt-parent-id=\"" + obj.id + "-l" + "\"><td>" + loss.name + "</td><td>" + loss.id + "</td><td></td></tr>");
                        });
                    }
                    addChildren(obj.id, backup);
                }
            });
            $("#hazardsTable").treetable({
                expandable: true,
                initialState: "collapsed",
                clickableNodeNames: true,
                indent: 30
            });
        }
    });

    $(window).ready(function () {
        var project_id = $("#project_id").val();
        $.ajax({
            "type": 'get',
            "url": '/systemsafetyconstraints/findByProjectId/' + project_id,
            "dataType": "json",
            "success": function (data) {
                $.each(data, function (idx, obj) {
                    console.log(obj);
                    $("#systemSafetyConstraintsTable").append("<tr data-tt-id=\"" + obj.id + "\" data-tt-parent-id=\"" + obj.father + "\"><td>" + obj.name + "</td><td>" + obj.id + "</td><td><button style='cursor: pointer; border-radius: 5px;' data-toggle='modal' data-target='#editSystemSafetyConstraintModal' onclick = loadEditSystemSafetyConstraint(this.id) type='button' id=\"" + obj.id + "\"><span class='fa fa-pencil' aria-hidden='true'></span></button><button style='cursor: pointer; border-radius: 5px;' data-toggle='modal' data-target='#confirmSystemSafetyConstraintDeleteModal' type='button' id=\"" + obj.id + "\" onclick = loadSystemSafetyConstraintToBeDeleted(this.id)><span class='fa fa-trash'></span></button></td></tr>");
                    $.each(obj.hazardEntities, function (idx, hazard) {
                        $("#systemSafetyConstraintsTable").append("<tr data-tt-id=\"" + obj.id + "-h-" + hazard.id + "\" data-tt-parent-id=\"" + obj.id + "\"><td>" + hazard.name + "</td><td>" + hazard.id + "</td><td></td></tr>");
                    });
                });
                $("#systemSafetyConstraintsTable").treetable({
                    expandable: true,
                    initialState: "collapsed",
                    clickableNodeNames: true,
                    indent: 30
                });
            }
        });
    });

});

function addChildren(id, backup){
   $.each(backup, function (idx, obj) {
        if(obj.father != null){
            if(obj.father.id == id){
            console.log(obj);
                $("#hazardsTable").append("<tr data-tt-id=\"" + obj.id + "\" data-tt-parent-id=\"" + obj.father.id + "\"><td>" + obj.name + "</td><td>" + obj.id + "</td><td><button style='cursor: pointer; border-radius: 5px;' data-toggle='modal' data-target='#editHazardModal' onclick = loadEditHazard(this.id) type='button' id=\"" + obj.id + "\"><span class='fa fa-pencil' aria-hidden='true'></span></button><button style='cursor: pointer; border-radius: 5px;' data-toggle='modal' data-target='#confirmHazardDeleteModal' type='button' id=\"" + obj.id + "\" onclick = loadHazardToBeDeleted(this.id)><span class='fa fa-trash'></span></button></td></tr>");
                if(obj.lossEntities.length > 0){
                    $("#hazardsTable").append("<tr data-tt-id=\"" + obj.id + "-l" + "\" data-tt-parent-id=\"" + obj.id + "\"><td>" + "Losses Associated" + "</td><td>" + "</td><td></td></tr>");
                    $.each(obj.lossEntities, function (idx, loss) {
                        $("#hazardsTable").append("<tr data-tt-id=\"" + obj.id + "-l-" + loss.id + "\" data-tt-parent-id=\"" + obj.id + "-l" + "\"><td>" + loss.name + "</td><td>" + loss.id + "</td><td></td></tr>");
                    });
                }
                addChildren(obj.id, backup);
            }
        }
   });
}

function collapseAllHazards(){
    $("#expandAllHazardsButton").attr("onclick","expandAllHazards()");
    $("#expandAllHazardsButton").text("Expand All");
    $("#hazardsTable").treetable("collapseAll");
}

function expandAllHazards(){
    $("#expandAllHazardsButton").attr("onclick","collapseAllHazards()");
    $("#expandAllHazardsButton").text("Collapse All");
    $("#hazardsTable").treetable("expandAll");
}

function collapseAllSystemSafetyConstraints(){
    $("#expandAllSystemSafetyConstraintsButton").attr("onclick","expandAllSystemSafetyConstraints()");
    $("#expandAllSystemSafetyConstraintsButton").text("Expand All");
    $("#systemSafetyConstraintsTable").treetable("collapseAll");
}

function expandAllSystemSafetyConstraints(){
    $("#expandAllSystemSafetyConstraintsButton").attr("onclick","collapseAllSystemSafetyConstraints()");
    $("#expandAllSystemSafetyConstraintsButton").text("Collapse All");
    $("#systemSafetyConstraintsTable").treetable("expandAll");
}