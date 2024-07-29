var lastOpenedModal;

$(window).ready(function () {
    var project_id = $("#project_id").val();

    $.ajax({
        "type": 'get',
        "url": '/system-goals/project/' + project_id,
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
        "url": '/assumptions/project/' + project_id,
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
        "url": '/losses/project/' + project_id,
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
        "url": '/hazards/project/' + project_id,
        "dataType": "json",
        "success": function (data) {
            var backup = data;
            $.each(data, function (idx, obj) {
                if(obj.father == null){
                    $("#hazardsTable").append("<tr data-tt-id=\"" + obj.id + "\" data-tt-parent-id=\"" + obj.father + "\"><td>" + obj.name + "</td><td>" + obj.id + "</td><td><button style='cursor: pointer; border-radius: 5px;' data-toggle='modal' data-target='#editHazardModal' onclick = loadEditHazard(this.id) type='button' id=\"" + obj.id + "\"><span class='fa fa-pencil' aria-hidden='true'></span></button><button style='cursor: pointer; border-radius: 5px;' data-toggle='modal' data-target='#confirmHazardDeleteModal' type='button' id=\"" + obj.id + "\" onclick = loadHazardToBeDeleted(this.id)><span class='fa fa-trash'></span></button></td></tr>");
                    if(obj.losses.length > 0){
                        $("#hazardsTable").append("<tr data-tt-id=\"" + obj.id + "-l" + "\" data-tt-parent-id=\"" + obj.id + "\"><td>" + "Losses Associated" + "</td><td>" + "</td><td></td></tr>");
                        $.each(obj.losses, function (idx, loss) {
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
            "url": '/system-safety-constraints/project/' + project_id,
            "dataType": "json",
            "success": function (data) {
                $.each(data, function (idx, obj) {
                    $("#systemSafetyConstraintsTable").append("<tr data-tt-id=\"" + obj.id + "\" data-tt-parent-id=\"" + obj.father + "\"><td>" + obj.name + "</td><td>" + obj.id + "</td><td><button style='cursor: pointer; border-radius: 5px;' data-toggle='modal' data-target='#editSystemSafetyConstraintModal' onclick = loadEditSystemSafetyConstraint(this.id) type='button' id=\"" + obj.id + "\"><span class='fa fa-pencil' aria-hidden='true'></span></button><button style='cursor: pointer; border-radius: 5px;' data-toggle='modal' data-target='#confirmSystemSafetyConstraintDeleteModal' type='button' id=\"" + obj.id + "\" onclick = loadSystemSafetyConstraintToBeDeleted(this.id)><span class='fa fa-trash'></span></button></td></tr>");
                    $.each(obj.hazards, function (idx, hazard) {
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

    $(".modal").on("show.bs.modal", function (e) {
        if($(this).attr('id') !== "errorModal"){
            lastOpenedModal = $(this).attr('id');
        }
    });

    $("#errorModal").on("hidden.bs.modal", function () {
        if (lastOpenedModal) {
            $("#" + lastOpenedModal).modal("show");
        }
    });

});

function addChildren(id, backup){
   $.each(backup, function (idx, obj) {
        if(obj.father != null){
            if(obj.father.id == id){
                $("#hazardsTable").append("<tr data-tt-id=\"" + obj.id + "\" data-tt-parent-id=\"" + obj.father.id + "\"><td>" + obj.name + "</td><td>" + obj.id + "</td><td><button style='cursor: pointer; border-radius: 5px;' data-toggle='modal' data-target='#editHazardModal' onclick = loadEditHazard(this.id) type='button' id=\"" + obj.id + "\"><span class='fa fa-pencil' aria-hidden='true'></span></button><button style='cursor: pointer; border-radius: 5px;' data-toggle='modal' data-target='#confirmHazardDeleteModal' type='button' id=\"" + obj.id + "\" onclick = loadHazardToBeDeleted(this.id)><span class='fa fa-trash'></span></button></td></tr>");
                if(obj.losses.length > 0){
                    $("#hazardsTable").append("<tr data-tt-id=\"" + obj.id + "-l" + "\" data-tt-parent-id=\"" + obj.id + "\"><td>" + "Losses Associated" + "</td><td>" + "</td><td></td></tr>");
                    $.each(obj.losses, function (idx, loss) {
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