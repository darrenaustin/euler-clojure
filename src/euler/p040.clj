(ns euler.p040
  (:use euler.numeric))

(defn solution {:answer 210} []
  (let [fraction (vec (take 1000000 (map digit->int (mapcat #(str %) (iterate inc 1)))))]
    (product
     (map #(nth fraction (dec %)) (take 7 (iterate #(* 10 %) 1))))))
