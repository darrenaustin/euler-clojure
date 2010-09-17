(ns dma.euler.p112
  (:use dma.euler.numeric))

(defn bouncy? [n]
  (let [ds (digits n)
        increasing? (fn [ds] (apply <= ds))
        decreasing? (fn [ds] (apply >= ds))]
    (and (not (increasing? ds))
         (not (decreasing? ds)))))

(defn least-bouncy-proportion [target]
  (loop [num-bouncy 0
         n 100]
    (if (= (/ num-bouncy (dec n)) target)
      (dec n)
      (recur (if (bouncy? n) (inc num-bouncy) num-bouncy) (inc n)))))

(defn solution {:answer nil} []
  (least-bouncy-proportion 99/100))
