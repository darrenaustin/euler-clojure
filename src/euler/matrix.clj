(ns euler.matrix)

(def small-matrix [
   [131,673,234,103, 18]
   [201, 96,342,965,150]
   [630,803,746,422,111]
   [537,699,497,121,956]
   [805,732,524, 37,331]
   ])

(def large-matrix
  (into [] (for [line (into [] (.split (slurp "data/matrix.txt") "\n"))]
                (into [] (map #(Integer/parseInt %) (.split line ","))))))

(defn width [matrix]
  (count (first matrix)))

(defn height [matrix]
  (count matrix))

(defn value [matrix [x y]]
  (get-in matrix [y x]))

(defn set-value [matrix [x y] value]
  (update-in matrix [y x] (constantly value)))
