const server = {
    url: 'http://localhost:8080',
    random: async function random(x, y) {
        let res = await fetch(`${this.url}/random?x=${x}&y=${y}`, {
            method: 'GET',
            mode: 'cors',
        })
        let json = await res.json()
        // console.log("init", json)
        return json
    },
    next: async function next(grid) {
        let res = await fetch(`${this.url}/next`, {
            method: 'POST',
            body: JSON.stringify({
                "grid": grid
            }),
            headers: {
                "Content-Type": "application/json"
            },
            mode: 'cors',
        })
        let json = await res.json()
        // console.log("next", json)
        return json
    },
    glider: async function glider() {
        let res = await fetch(`${this.url}/glider`, {
            method: 'GET',
            mode: 'cors',
        })
        let json = await res.json()
        // console.log("init", json)
        return json
    },
}

let sketch = function (p) {
    const domCont = document.getElementById('canvas-cont')
    const domGridX = document.getElementById('grid-x')
    const domGridY = document.getElementById('grid-y')
    const domDelay = document.getElementById('refresh-delay')
    const domShowCoords = document.getElementById('show-coords')
    const domInitFn = document.getElementById('init-fn')

    let canvas
    let gridRows = parseInt(domGridX.value)
    let gridCols = parseInt(domGridY.value)
    let initFn = domInitFn.value

    let contrast = "#ac3939"
    let defaultStrokeColor = "#d9d9d9"
    let defaultFillColor = "#84b16b"
    let white = "#F5F5F5"

    let gridState = [[]]

    p.setup = async function () {
        canvas = p.createCanvas(400, 400)
        canvas.parent(domCont)
        gridState = await initState(gridRows, gridCols, initFn)
    }

    p.draw = async function () {
        let oldGridRows = gridRows
        let oldGridCols = gridCols
        let oldInitFn = initFn
        gridRows = parseInt(domGridX.value)
        gridCols = parseInt(domGridY.value)
        initFn = domInitFn.options[domInitFn.selectedIndex].value


        // reset grid if size changed
        if (oldGridRows !== gridRows || oldGridCols !== gridCols || oldInitFn !== initFn) {
            gridState = await initState(gridRows, gridCols, initFn)
        } else {
            gridState = await updateState(gridState)
        }

        let delay = parseInt(domDelay.value)
        let showCoords = domShowCoords.checked

        if (delay) {
            let fps = 1 / (delay * 0.001)
            p.frameRate(fps)
        }

        // use the longest axis to calc square side length
        let actualXlen = gridState.length
        let actualYlen = gridState[0].length
        let squareSide = actualXlen > actualYlen ? canvas.width / actualXlen : canvas.width / actualYlen

        // Draw
        p.background(white)
        p.stroke(defaultStrokeColor)
        drawState(gridState, squareSide, showCoords)
    }

    function drawState(rows, squareSide, showCoords) {
        for (let row = 0; row < rows.length; row++) {
            for (let col = 0; col < rows[row].length; col++) {
                let alive = rows[row][col]
                alive ? p.fill(defaultFillColor) : p.fill(white)
                let rowPos = row * squareSide
                let colPos = col * squareSide
                p.square(colPos, rowPos, squareSide)
                if (showCoords) {
                    p.textSize(12)
                    p.fill(contrast)
                    p.stroke(contrast)
                    rowPos = rowPos + (squareSide / 2)
                    colPos = colPos + (squareSide / 2)
                    p.text(`${col},${row}`, colPos, rowPos)
                    p.stroke(defaultStrokeColor)
                }
            }
        }
    }

    async function initState(gridX, gridY, serverFn) {
        console.log(`init with ${serverFn}`)
        return (await server[serverFn](gridX, gridY)).grid
    }

    async function updateState(state) {
        return (await server.next(state)).grid
    }

    async function randomArray2d(x, y) {
        return new Array(x).fill([]).map(() =>
            new Array(y).fill(0).map(() => Math.round(Math.random()))
        )
    }
}

new p5(sketch)
