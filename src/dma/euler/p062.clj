(ns dma.euler.p062
  (:use dma.euler.numeric))

;; Construct a map where the key is a sorted list of digits that make up
;; the cube of any given number, and the value is a list of numbers that
;; when cubed yield that set of digits
(defn cube-digit-map [max-n]
  (reduce (fn [cs n]
            (let [n3 (cube n), ds (sort (digits n3))]
              (assoc cs ds (conj (cs ds) n)))) {} (range 1 max-n)))

(defn solution {:answer 127035954683} []
  (cube (apply min (first (filter #(>= (count %) 5) (vals (cube-digit-map 10000)))))))
