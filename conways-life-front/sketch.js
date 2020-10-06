const server = {
    url: 'http://localhost:8080',
    init: async function init(x, y) {
        let res = await fetch(`${this.url}/init?x=${x}&y=${y}`, {
            method: 'GET',
            mode: 'cors',
        })
        let json = res.json()
        console.log(json)
        return json
    },
    next: async function next(grid) {
        let res = await fetch(`${this.url}/next`, {
            method: 'POST',
            body: {
                "grid": grid
            },
            mode: 'cors',
        })
        let json = res.json()
        console.log(json)
        return json
    }
}

let sketch = function (p) {
    const domCont = document.getElementById('canvas-cont')
    const domGridX = document.getElementById('grid-x')
    const domGridY = document.getElementById('grid-y')
    const domDelay = document.getElementById('refresh-delay')
    const domShowCoords = document.getElementById('show-coords')

    let canvas
    let gridX = parseInt(domGridX.value)
    let gridY = parseInt(domGridY.value)

    let contrast = "#ac3939"
    let defaultStrokeColor = "#d9d9d9"
    let defaultFillColor = "#84b16b"
    let white = "#F5F5F5"

    let gridState;

    p.setup = async function () {
        canvas = p.createCanvas(400, 400)
        canvas.parent(domCont)
        gridState = await initState(gridX, gridY)
    }

    p.draw = async function () {
        let oldGridX = gridX
        let oldGridY = gridY
        gridX = parseInt(domGridX.value)
        gridY = parseInt(domGridY.value)

        // reset grid if size changed
        if(oldGridX !== gridX || oldGridY !== gridY){
            await initState(gridX, gridY)
        }

        let delay = parseInt(domDelay.value)
        let showCoords = domShowCoords.checked

        if (delay) {
            let fps = 1 / (delay * 0.001)
            p.frameRate(fps)
        }

        // use the longest axis to calc square side length
        let squareSide = gridX > gridY ? canvas.width / gridX : canvas.width / gridY

        // Draw
        p.background(white)
        p.stroke(defaultStrokeColor);
        drawState(gridState, squareSide, showCoords)

        gridState = await updateState(gridState)
    }

    function drawState(state, squareSide, showCoords) {
        for (let x = 0; x < state.length; x++) {
            for (let y = 0; y < state[x].length; y++) {
                let alive = state[x][y]
                alive ? p.fill(defaultFillColor) : p.fill(white)
                let xPos = x * squareSide
                let yPos = y * squareSide
                p.square(xPos, yPos, squareSide)
                if (showCoords) {
                    p.textSize(12);
                    p.fill(contrast)
                    p.stroke(contrast)
                    xPos = xPos + (squareSide / 2)
                    yPos = yPos + (squareSide / 2)
                    p.text(`${x},${y}`, xPos, yPos);
                    p.stroke(defaultStrokeColor);
                }
            }
        }
    }

    async function initState(gridX, gridY) {
        return server.init(gridX, gridY)
    }

    async function updateState(gridState) {
        return server.next(gridState)
    }

    async function randomArray2d(x, y) {
        return new Array(x).fill([]).map(() =>
            new Array(y).fill(0).map(() => Math.round(Math.random()))
        );
    }
}

new p5(sketch)
