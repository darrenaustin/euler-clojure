(ns dma.euler.p023
  (:use dma.euler.numeric))

(defn sum-of-abundants? [n abundants]
  (some #(abundants (- n %)) (take-while-< n abundants)))

(defn solution {:answer 4179871} []
  (let [abundants (into (sorted-set) (filter abundant? (range 12 28124)))]
    (sum (filter #(not (sum-of-abundants? % abundants)) (range 1 28124)))))
