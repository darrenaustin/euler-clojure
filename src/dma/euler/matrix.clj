(ns dma.euler.matrix
  (:use clojure.contrib.duck-streams))

(def *small-matrix* [
   [131,673,234,103, 18]
   [201, 96,342,965,150]
   [630,803,746,422,111]
   [537,699,497,121,956]
   [805,732,524, 37,331]
   ])

(def *large-matrix*
     (into [] (for [line (read-lines "data/matrix.txt")]
                (into [] (map #(Integer/parseInt %) (.split line ","))))))

(defn width [matrix]
  (count (first matrix)))

(defn height [matrix]
  (count matrix))

(defn value [matrix [x y]]
  (-> matrix (nth y) (nth x)))

(defn set-value [matrix [x y] value]
  (assoc matrix y (assoc (nth matrix y) x value)))


;; Support for building and manipulating transient matrices
(defn transient-matrix [matrix]
  (let [m (transient matrix)]
    (doall
     (for [y (range 0 (count m))]
       (assoc! m y (transient (nth m y)))))
    m))

(defn set-value! [matrix [x y] value]
  (assoc! matrix y (assoc! (nth matrix y) x value)))

(defn persistent-matrix! [matrix]
  (doall
   (for [y (range 0 (count matrix))]
       (assoc! matrix y (persistent! (nth matrix y)))))
  (persistent! matrix))
