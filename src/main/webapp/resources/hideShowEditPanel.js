
    function showEditPanel(id) {
        var del = document.getElementById('delete' + id)
        var delTd = document.getElementById('deleteTd' + id)
        var note = document.getElementById('addNote' + id)
        // var noteTd = document.getElementById('addNoteTd' + id)


        if (del != null) {
            del.style.display = 'block';
        }
        if(delTd != null){
            delTd.style.background = 'white';
        }
        if(note != null){
            note.style.display = 'block';
        }
        // if(noteTd != null){
        //     noteTd.style.background = 'white';
        // }
    }
    function hideEditPanel(id){
        var del = document.getElementById('delete' + id)
        var delTd = document.getElementById('deleteTd' + id)
        var note = document.getElementById('addNote' + id)
        // var noteTd = document.getElementById('addNoteTd' + id)

        if (del != null) {
            del.style.display = 'none';
        }
        if(delTd != null){
            delTd.style.background = 'none';
        }
        if(note != null){
            note.style.display = 'none';
        }
        // if(noteTd != null){
        //     noteTd.style.background = 'none';
        // }
    }
