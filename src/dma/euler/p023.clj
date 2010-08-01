(ns dma.euler.p023
  (:use dma.euler.numeric))

(defn sum-of-abundants? [n abundants]
  (some #(abundants (- n %)) (take-while-< n abundants)))

(defn solution {:answer 4179871} []
  (let [abundants (into (sorted-set) (filter abundant? (range 12 28124)))]
    (sum (filter #(not (sum-of-abundants? % abundants)) (range 1 28124)))))

;; My slower first solution:
;;
;; (defn p023
;;   {:answer 4179871}
;;   []
;;   (let [abundants (vec (filter abundant? (range 12 28124)))
;;              sum-abundants (into (sorted-set)
;;                                                      (for [a (range (count abundants))
;;                                                                b (range a (count abundants))]
;;                                                        (+ (nth abundants a) (nth abundants b))))]
;;      (sum (filter #(not (contains? sum-abundants %)) (range 1 28124)))))

