function handleNewBtn(el) {
    el.click(function () {
        jQuery.ajax({
            data: {
                value: $('input#scaffold_value_input').val(),
                type: $('input#scaffold_type').val(),
                parent: $('input#scaffold_parent').val(),
                parentId: $('input#scaffold_parent_id').val()
            },
            url: "/Administration/newValue",
            success: function (data) {
                $("ul#scaffold_sublist").html(generateLi(data));
                $('#myModal').modal('toggle');

            }
        });
//        return false;
    });


}
$().ready(function () {
    var selector = $("#scaffold_new_save");
    if (selector.size() > 0) {
        handleNewBtn(selector);
    }
});