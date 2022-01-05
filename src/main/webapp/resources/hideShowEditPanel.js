
    function showEditPanel(id){
        document.getElementById('delete' + id).style.display = 'block';
        document.getElementById('deleteTd' + id).style.background = 'white';
    }
    function hideEditPanel(id){
        document.getElementById('delete' + id).style.display = 'none';
        document.getElementById('deleteTd' + id).style.background = 'none';
    }
