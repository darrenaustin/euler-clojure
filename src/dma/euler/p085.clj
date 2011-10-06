(ns dma.euler.p085
  (:use dma.euler.numeric)
  (:use clojure.contrib.math))

;; For a given W x H grid, a rectangle will have W+1 vertical grid
;; lines and H+1 horizontal grid lines.  Any given rectangle in this
;; grid will have two vertical and two horizontal lines.  So the
;; number of rectangles in the grid will be C(W+1, 2) * C(H+1, 2).
;; Which expanded is: W(W+1)/2 * H(H+1)/2 => W(W+1) * H(H+1) / 4
(defn num-rectangles [width height]
  (/ (* width (inc width) height (inc height)) 4))


(defn solution {:answer 2772} []
  ;; Given that (num-rectangles 100 100) is ~25mil we can use that
  ;; as an upper bound
  (let [target 2000000 max-side 100
        [best-distance best-area] 
          (reduce (fn [[best-distance best-area] [distance area]]
                    (if (< distance best-distance)
                      [distance area]
                      [best-distance best-area]))
                  (for [w (range max-side) h (range w max-side)]
                    [(abs (- target (num-rectangles w h))) (* w h)]))]
    best-area))



  
