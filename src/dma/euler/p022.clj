(ns dma.euler.p022
  (:use [dma.euler numeric util]))

(defn name-score [index name]
  (* (inc index) (sum (map #(- (int %) (dec (int \A))) name))))

(defn solution {:answer 871198282} []
  (let [names (sort (words-from "data/names.txt"))]
    (sum (map-indexed name-score names))))
