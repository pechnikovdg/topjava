const mealsAjaxUrl = "ui/meals/";

const ctx = {
    ajaxUrl: mealsAjaxUrl
};

$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ]
        })
    );
});

function filter() {
    const filterForm = $('#filterForm');
    $.ajax({
        type: "GET",
        url: ctx.ajaxUrl  + "filter",
        data: filterForm.serialize()
    }).done(function (filteredData) {
        updateTableFiltered(filteredData);
        successNoty("Filtered");
    })
}

function updateTableFiltered(filteredData) {
    ctx.datatableApi.clear().rows.add(filteredData).draw();
}

