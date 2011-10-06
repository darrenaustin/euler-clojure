(ns dma.euler.p081
  (:use dma.euler.matrix))

(defn solution {:answer 427337} []
  (let [m *large-matrix* w (width m) h (height m)]
    ;; Propagate the sum of the smallest path at each point by adding
    ;; the minimum of the down and right cells.  If we do this from the
    ;; bottom right and work our way up to the upper left (0, 0), then
    ;; the value of the upper left will be the sum of the minimal path
    ;;
    (get-in
     (reduce (fn [m [y x]]
               (let [down (get-in m [(inc y) x])
                     right (get-in m [y (inc x)])]
                 (update-in m [y x] + (if (and down right) (min down right) (or down right 0)))))
             m
             (for [y (reverse (range h)) x (reverse (range w))]
               [y x]))
     [0 0])))
