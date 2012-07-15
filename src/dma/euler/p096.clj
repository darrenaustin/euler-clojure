(ns dma.euler.p096)

(defn cross [A B]
  (for [a A b B] (str a b)))

(def digits "123456789")
(def rows "ABCDEFGHI")
(def cols digits)
(def squares (cross rows cols))

;(def cols (for [y ds] (for [x ds] [x y])))
;(def rows (for [y ds] (for [x ds] [y x])))
;(def boxes (for [by (range 3) bx (range 3)]
;             (for [y (range 3) x (range 3)]
;               [(+ (* bx 3) x) (+ (* by 3) y)])))
;(def groups (concat cols rows boxes))

(defn read-puzzles []
  (map (fn [board-data]
         (vec (map #(vec (replace {\0 nil} %)) (rest board-data))))
       (partition 10 (.split (slurp "data/sudoku.txt") "\n"))))

(defn print-puzzle [puzzle]
  (let [row-divider "+-------+-------+-------+"]
    (doseq [y (range 9)]
      (when (zero? (rem y 3))
        (println row-divider))
      (println (apply format "| %s %s %s | %s %s %s | %s %s %s |"
                      (map #(or % \space) (puzzle y)))))
    (println row-divider)))

;; TODO: finish this
(defn solution- {:answer 24702} []
  (let [puzzles (read-puzzles)]
    (print-puzzle (first puzzles))))
