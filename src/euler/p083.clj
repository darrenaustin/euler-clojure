(ns euler.p083
  (:use euler.core)
  (:use euler.matrix)
  (:use euler.graph))

(defn h [graph [n-x n-y] [goal-x goal-y]]
  (+ (abs (- n-x goal-x)) (abs (- n-y goal-y))))

(defn neighbors [graph pos]
  (let [w (width graph)
        h (height graph)]
    (filter (fn [[x y]] (and (< -1 x w) (< -1 y h)))
            (for [d [[0 -1] [1 0] [0 1] [-1 0]]]
              (map + pos d)))))

(defn movement-cost [graph start end]
  (value graph end))

(defn solution
  ([] (solution *large-matrix*))
  ([matrix]
     (when-let [path (a* matrix [0 0] [(dec (width matrix)) (dec (height matrix))] h neighbors movement-cost)]
       (reduce + (map #(value matrix %) path)))))