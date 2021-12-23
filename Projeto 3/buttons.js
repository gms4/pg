class Buttons{
    constructor(){}

    alternateButton(mx, my){
    if(mx < width/10 && my < btnHeight){
        board.alternate()
    }
    }
    addButton(mx, my, board){
    if(mx < width/10 && my > btnHeight && my < 2*btnHeight){
        creating = !creating
        if(creating){
            board.curves.push(new Curve([]))
        }
        board.selected = board.curves.length-1
    }
    }
    deleteButton(mx, my, board){
    if(mx < width/10 && my > 2*btnHeight && my < 3*btnHeight){
        board.delete()
    }
    }
    evaluationsButton(mx, my){
    if(mx < width/10 && my > 3*btnHeight && my < 4*btnHeight){
        board.alternateEvaluationNumber()
    }
    }
    viewButton(mx, my){
        if(mx < width/10 && my > 4*btnHeight && my < 5*btnHeight){
        board.view()
        viewAll = !viewAll
    }
    }
    viewPointsButton(mx, my){
    if(mx < width/10 && my > 5*btnHeight && my < 6*btnHeight){
        viewPoints = !viewPoints
        board.viewPoints = viewPoints
    }
    }
    viewPolysButton(mx, my){
    if(mx < width/10 && my > 6*btnHeight && my < 7*btnHeight){
        viewPolys = !viewPolys
        board.viewPolys = viewPolys
    }
    }
    viewCurvesButton(mx, my){
    if(mx < width/10 && my > 7*btnHeight && my < 8*btnHeight){
        viewCurves = !viewCurves
        board.viewCurves = viewCurves
    }
    }
}