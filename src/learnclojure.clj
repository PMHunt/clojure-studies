(ns learnclojure)

(require '[clojure.repl :refer [doc]]
         )

(print "Hello World")

(def x "Hello Clojure")

(let [x "Steve"]
  (print "Hello" x))

(if (empty? x)
  "X is emptry"
  "X is not empty")

(if (empty? x)
  nil
  (do
    (println "OK")
    :ok))

(reverse x)

(apply str (reverse x))

(fn [] "Hello, I have less brackets")

(defn hello [name] (str "Hello, " name  " I have less brackets"))

(hello "Phil")

(doc hello)

(defn my-set [x y]
  "JoC2 book version won't make a set using e.g. a a, this work around this"
  (println (str "Make me a set bitch. Use " x " and " y))
  (set (vector x y)))

(my-set 'a 'bq)

;; 4Clojure #29 - return seq of upper case chars only

(= (#(clojure.string/join (re-seq #"[A-Z]" %)) "HeLlO, WoRlD!") "HLOWRD")

;; 4Clojure #30 - remove consecutive dupliates and return

(= (apply str ( #(dedupe (seq %)) "Leeeeeerrroyyy")) "Leroy")

(= ( #(map first (partition-by identity %)) [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))

;; 4Clojure #39 - interleave

(= (#(map first [%1 %2]) [1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c))
; I think I need to understand 'recur' and/or 'loop' here, vs CAR/CDR recursion

(defn print-down-from [x]
  "using recur to make a print loop, think of it as a block with 'when' test.
   Recur takes (dec x) as a parameter and rebinds calling fn param to result,
   it then passes control back to calling fn (or to loop if you need closure)"
  (when (pos? x)
    (println x)
    (recur (dec x))))

(defn sum-down-from [sum x]
  "This time we make an accumulator"
  (if (pos? x)
    (recur (+ sum x) (dec x))
    sum))

(defn loopy-sum-down-from [initial-x]
  "We use loop as a 'let' that's also recur's rebinding target"
  (loop [sum 0 x initial-x]
    (if (pos? x)
      (recur (+ sum x) (dec x))
      sum)))

(def guys-whole-name ["Guy" "Lewis" "Steele"])

(let [[f-name m-name l-name] guys-whole-name]
  (str l-name ", " f-name " " m-name))

;; 4Clojure #49
(= (__ 3 [1 2 3 4 5 6]) [[1 2 3] [4 5 6]])

(defn my-split [n coll]
  [(take n coll) (drop n coll )])

(defrecord recipe [name
                   ingredients
                   ])

(def quiche
  (->recipe "Quiche" '(eggs)))

(def quiche
  (map->recipe {:name "Quiche"
                :ingredients '(eggs buttter milk flour salt)}))
(defn say-hi []
  (println "Hello from thread " (.getName (Thread/currentThread))))

(dotimes [_ 3]
  (.start (Thread. say-hi)))

(.toString x)
;; Java: x.toString()

(.addVector2D v v2)
;; Java: v.addVector2D(v2)

(require '[clojure.string :as str])

(defn ellipsize [words]
  (let [[x y z] (str/split words #"\s+")] ; split on whitespace and destruct.
    (str/join " " [x y z " "])))
