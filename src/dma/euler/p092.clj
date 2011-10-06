(ns dma.euler.p092
  (:use dma.euler.numeric))

;; Cleaner, but slow way to get the next number in the chain
;;
;; (defn sum-square-digits [n]
;;  (sum (map square (digits n))))

(def squares (vec (map square (range 10))))

(defn sum-square-digits [n]
  (loop [n n sum 0]
    (if (zero? n)
      sum
      (recur (quot n 10) (+ sum (squares (rem n 10)))))))

(let [chain-endings (atom {1 1 89 89})]
  (defn chain-end [n]
    (loop [n n chain []]
      (if-let [end (@chain-endings n)]
        (do
          (if (seq chain)
            (swap! chain-endings #(apply assoc % (interleave chain (repeat end)))))
          end)
        (recur (sum-square-digits n) (conj chain n))))))

(defn solution {:answer 8581146} []
  ;; Note that we pass the number through sum-square-digits first
  ;; to get it down to a number less than 567 (for 9999999).  That
  ;; keeps the cache manageable
  (count (filter #(= 89 (chain-end (sum-square-digits %))) (range 1 10000000))))
