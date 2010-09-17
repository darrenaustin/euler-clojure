(ns dma.euler.p112
  (:use dma.euler.numeric))

(defn increasing? [digits]
  (apply <= digits))

(defn decreasing? [digits]
  (apply >= digits))

(defn bouncy? [n]
  (let [ds (digits n)]
    (and (not (increasing? ds))
         (not (decreasing? ds)))))

;; More functional, but slower approach.  Would like to see if
;; there is a more concise way to do this
;;
;; (defn least-bouncy-proportion [target]
;;   (second (first (filter (fn [[num-bouncy n]] (= (/ num-bouncy n) target))
;;                          (reductions
;;                           (fn [[num-bouncy n] x]
;;                             [(if (bouncy? x) (inc num-bouncy) num-bouncy) x])
;;                           [0 99]
;;                           (iterate inc 100))))))

(defn least-bouncy-proportion [target]
  (loop [num-bouncy 0, n 100]
    (if (= (/ num-bouncy n) target)
      n
      (recur (if (bouncy? (inc n)) (inc num-bouncy) num-bouncy) (inc n)))))

(defn solution {:answer 1587000} []
  (least-bouncy-proportion 99/100))
