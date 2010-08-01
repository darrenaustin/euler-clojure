(ns dma.euler.p022
  (:use dma.euler.numeric clojure.contrib.seq-utils))

(defn name-score [[index name]]
  (* (inc index) (sum (map #(- (int %) (dec (int \A))) name))))

(defn solution {:answer 871198282} []
  (let [names (sort (map #(.replace % "\"" "")
                         (.split (slurp "data/names.txt") ",")))]
    (sum (map name-score (indexed names)))))



