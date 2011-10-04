(ns dma.euler.p074
  (:use dma.euler.numeric))

(def digit-factorial (vec (map factorial (range 10))))

(defn sum-factorial-digits [n]
  (sum (map digit-factorial (digits n))))

(def chain-length
     (let [chains (atom {})]
       (letfn [(update-lengths! [chain length]
                 (loop [c chain l length]
                   (when (seq c)
                     (swap! chains assoc (first c) (inc l))
                     (recur (rest c) (inc l))))
                 (+ length (count chain)))
               (chain-length [n chain seen]
                  (if-let [n-length (@chains n)]
                    (update-lengths! chain n-length)
                    (if (seen n)
                      (update-lengths! chain 0)
                      (recur (sum-factorial-digits n) (conj chain n) (conj seen n)))))]
         (fn [n] (chain-length n () #{})))))

(defn solution {:answer 402} []
  (count (filter #(= 60 (chain-length %)) (range 1000000))))
