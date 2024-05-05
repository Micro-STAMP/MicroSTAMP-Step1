$(window).ready(function () {
    var project_id = $("#project_id").val();
    $.ajax({
        "type": 'get',
        "url": '/guests-request/systemgoals/' + project_id,
        "dataType": "json",
        "success": function (data) {
            $.each(data, function (idx, obj) {
                $("#guestsSystemGoalsTable").append("<tr data-tt-id=\"" + obj.id + "\" data-tt-parent-id=\"" + obj.father + "\"><td>" + obj.name + "</td><td>" + obj.id + "</td><td></td></tr>");
            });
            $("#guestsSystemGoalsTable").treetable({
                expandable: true,
                initialState: "expanded",
                clickableNodeNames: true,
                indent: 30
            });
        }
    });

    $.ajax({
        "type": 'get',
        "url": '/guests-request/assumptions/' + project_id,
        "dataType": "json",
        "success": function (data) {
            $.each(data, function (idx, obj) {
                $("#guestsAssumptionsTable").append("<tr data-tt-id=\"" + obj.id + "\" data-tt-parent-id=\"" + obj.father + "\"><td>" + obj.name + "</td><td>" + obj.id + "</td><td></td></tr>");
            });
            $("#guestsAssumptionsTable").treetable({
                expandable: true,
                initialState: "expanded",
                clickableNodeNames: true,
                indent: 30
            });
        }
    });

    $.ajax({
        "type": 'get',
        "url": '/guests-request/losses/' + project_id,
        "dataType": "json",
        "success": function (data) {
            $.each(data, function (idx, obj) {
                $("#guestsLossesTable").append("<tr data-tt-id=\"" + obj.id + "\" data-tt-parent-id=\"" + obj.father + "\"><td>" + obj.name + "</td><td>" + obj.id + "</td><td></td></tr>");
            });
            $("#guestsLossesTable").treetable({
                expandable: true,
                initialState: "expanded",
                clickableNodeNames: true,
                indent: 30
            });
        }
    });

    $.ajax({
        "type": 'get',
        "url": '/guests-request/hazards/' + project_id,
        "dataType": "json",
        "success": function (data) {
            var backup = data;
            $.each(data, function (idx, obj) {
                if(obj.father == null){
                    $("#guestsHazardsTable").append("<tr data-tt-id=\"" + obj.id + "\" data-tt-parent-id=\"" + obj.father + "\"><td>" + obj.name + "</td><td>" + obj.id + "</td><td></td></tr>");
                    if(obj.lossEntities.length > 0){
                        $("#guestsHazardsTable").append("<tr data-tt-id=\"" + obj.id + "-l" + "\" data-tt-parent-id=\"" + obj.id + "\"><td>" + "Losses Associated" + "</td><td>" + "</td><td></td></tr>");
                        $.each(obj.lossEntities, function (idx, loss) {
                            $("#guestsHazardsTable").append("<tr data-tt-id=\"" + obj.id + "-l-" + loss.id + "\" data-tt-parent-id=\"" + obj.id + "-l" + "\"><td>" + loss.name + "</td><td>" + loss.id + "</td><td></td></tr>");
                        });
                    }
                    addChildren(obj.id, backup);
                }
            });
            $("#guestsHazardsTable").treetable({
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
                "url": '/guests-request/systemsafetyconstraints/' + project_id,
                "dataType": "json",
                "success": function (data) {
                    $.each(data, function (idx, obj) {
                        console.log(obj);
                        $("#guestsSystemSafetyConstraintsTable").append("<tr data-tt-id=\"" + obj.id + "\" data-tt-parent-id=\"" + obj.father + "\"><td>" + obj.name + "</td><td>" + obj.id + "</td><td></td></tr>");
                        $.each(obj.hazardEntities, function (idx, hazard) {
                            $("#guestsSystemSafetyConstraintsTable").append("<tr data-tt-id=\"" + obj.id + "-h-" + hazard.id + "\" data-tt-parent-id=\"" + obj.id + "\"><td>" + hazard.name + "</td><td>" + hazard.id + "</td><td></td></tr>");
                        });
                    });
                    $("#guestsSystemSafetyConstraintsTable").treetable({
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
                $("#guestsHazardsTable").append("<tr data-tt-id=\"" + obj.id + "\" data-tt-parent-id=\"" + obj.father.id + "\"><td>" + obj.name + "</td><td>" + obj.id + "</td><td></td></tr>");
                if(obj.lossEntities.length > 0){
                    $("#guestsHazardsTable").append("<tr data-tt-id=\"" + obj.id + "-l" + "\" data-tt-parent-id=\"" + obj.id + "\"><td>" + "Losses Associated" + "</td><td>" + "</td><td></td></tr>");
                    $.each(obj.lossEntities, function (idx, loss) {
                        $("#guestsHazardsTable").append("<tr data-tt-id=\"" + obj.id + "-l-" + loss.id + "\" data-tt-parent-id=\"" + obj.id + "-l" + "\"><td>" + loss.name + "</td><td>" + loss.id + "</td><td></td></tr>");
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
    $("#guestsHazardsTable").treetable("collapseAll");
}

function expandAllHazards(){
    $("#expandAllHazardsButton").attr("onclick","collapseAllHazards()");
    $("#expandAllHazardsButton").text("Collapse All");
    $("#guestsHazardsTable").treetable("expandAll");
}

function collapseAllSystemSafetyConstraints(){
    $("#expandAllSystemSafetyConstraintsButton").attr("onclick","expandAllSystemSafetyConstraints()");
    $("#expandAllSystemSafetyConstraintsButton").text("Expand All");
    $("#guestsSystemSafetyConstraintsTable").treetable("collapseAll");
}

function expandAllSystemSafetyConstraints(){
    $("#expandAllSystemSafetyConstraintsButton").attr("onclick","collapseAllSystemSafetyConstraints()");
    $("#expandAllSystemSafetyConstraintsButton").text("Collapse All");
    $("#guestsSystemSafetyConstraintsTable").treetable("expandAll");
}