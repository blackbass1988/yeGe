function generateLi(data){
    var newList = "";
    for (var i=0; i< data.length; i++) {
        newList += "<li>" + data[i].value + "</li>"
    }
    return newList;
}

function handleProjectName(el) {
    el.change(function() {
        var selectedId = $(this).find(":selected").val();
        $("#scaffold_parent_id").val(selectedId);
        jQuery.ajax({
            url: "/getTemplatesForProject/",
            data: {
                project : selectedId
            },
            success: function(data) {
              console.info(data);
                var prEnabledTemplatesList = $("ul#scaffold_sublist");
                prEnabledTemplatesList.html("");
                var newList = generateLi(data);
                prEnabledTemplatesList.html(newList);
            }
        })
    })
}

$().ready(function(){
    var selector = $("select#project_name");
    if (selector.size() > 0) {
        handleProjectName(selector);
    }
});