(ns dma.euler.p054
  (:refer-clojure :exclude [flush])
  (:use [dma.euler numeric util] [clojure.java io]))

(defstruct card :value :suit :sort-value)

(def card-value (indexed-map ["2" "3" "4" "5" "6" "7" "8" "9" "T" "J" "Q" "K" "A"] 2))

(defn create-card [s]
  (let [value (subs s 0 1), suit (subs s 1 2)]
    (struct card value suit (card-value value))))

(defn card-name [card]
  (str (:value card) (:suit card)))

(defn compare-cards [c1 c2]
  (compare (:sort-value c1) (:sort-value c2)))

(def score-value (indexed-map [:high-card :one-pair :two-pairs :three-of-a-kind
                               :straight :flush :full-house :four-of-a-kind
                               :straight-flush :royal-flush]))

(defn flush? [cards]
  (apply = (map :suit cards)))

(defn straight? [cards]
  (let [first-value (:sort-value (first cards))]
    (= (map :sort-value cards) (range first-value (+ first-value 5)))))

(defn runs [cards]
  (loop [runs [], value nil, length 0, c 0]
    (if (= c (count cards))
      (sort (conj runs [length value]))
      (let [c-value (:sort-value (nth cards c))]
        (if (not= value c-value)
          (if value
            (recur (conj runs [length value]) c-value 1 (inc c))
            (recur runs c-value 1 (inc c)))
          (recur runs c-value (inc length) (inc c)))))))

(defstruct hand :cards :flush? :straight? :runs)

(defn create-hand [card-names]
  (let [cards (sort compare-cards (map create-card card-names))]
    (struct hand
            cards
            (flush? cards)
            (straight? cards)
            (runs cards))))

(defn hand-str [hand]
  (apply str (interpose " " (map card-name (:cards hand)))))

(defn royal-flush [hand]
  (when (and (:flush? hand) (:straight? hand) (= "T" (:value (first (:cards hand)))))
    [(score-value :royal-flush)]))

(defn straight-flush [hand]
  (when (and (:flush? hand) (:straight? hand))
    [(score-value :straight-flush) (:sort-value (last (:cards hand)))]))

(defn four-of-a-kind [hand]
  (when-let [[length value] (last (:runs hand))]
    (when (= length 4)
      [(score-value :four-of-a-kind) value])))

(defn full-house [hand]
  (when (and (= (count (:runs hand)) 2))
    (let [[trip-length trip-value] (second (:runs hand))
          [pair-length pair-value] (first (:runs hand))]
      (when (and (= trip-length 3) (= pair-length 2))
        [(score-value :full-house) [trip-value pair-value]]))))

(defn flush [hand]
  (when (:flush? hand)
    [(score-value :flush) (:sort-value (last (:cards hand)))]))

(defn straight [hand]
  (when (:straight? hand)
    [(score-value :straight) (:sort-value (last (:cards hand)))]))

(defn three-of-a-kind [hand]
  (when-let [[length value] (last (:runs hand))]
    (when (= length 3)
      [(score-value :three-of-a-kind) value])))

(defn two-pairs [hand]
  (when (and (= (count (:runs hand)) 3))
    (let [[high-length high-value] (last (:runs hand))
          [low-length low-value] (second (:runs hand))]
      (when (and (= high-length 2) (= low-length 2))
        [(score-value :two-pairs) [high-value low-value]]))))

(defn one-pair [hand]
  (when-let [[length value] (last (:runs hand))]
    (when (= length 2)
      [(score-value :one-pair) value])))

(defn high-card [hand]
  [(score-value :high-card) (:sort-value (last (:cards hand)))])

(defn score [hand]
  (or (royal-flush hand)
      (straight-flush hand)
      (four-of-a-kind hand)
      (full-house hand)
      (flush hand)
      (straight hand)
      (three-of-a-kind hand)
      (two-pairs hand)
      (one-pair hand)
      (high-card hand)))

(defn compare-hands [h1 h2]
  (let [compare-scores (compare (score h1) (score h2))]
    (if (zero? compare-scores)
      (compare-seqs (reverse (map :sort-value (:cards h1)))
               (reverse (map :sort-value (:cards h2))))
      compare-scores)))

(defn solution {:answer 376} []
  (let [hands 
         (with-open [rdr (reader "data/poker.txt")]
           (doall (for [line (line-seq rdr)]
                    (vec (map create-hand (partition 5 (.split line " ")))))))]
    (count
     (filter #(= (compare-hands (first %) (second %)) 1) hands))))
