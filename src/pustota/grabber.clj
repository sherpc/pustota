(ns pustota.grabber
  (:use [clojure.core.async :only [chan go >!]])
  (:require [net.cgrand.enlive-html :as html]))

(def pustota-url "http://vpustotu.ru/moderation/")

(defn fetch-url [url]
  (html/html-resource (java.net.URL. url)))

(defn parse-url
  []
  (-> pustota-url
    fetch-url
    (html/select [:div.fi_text])
    first
    html/text))

(defn grab
  "Создает workers потоков и возвращает канал."
  [workers]
  (let [c (chan)]
    (do
      (doseq [i (range workers)]
        (go
          (Thread/sleep (rand-int 1000))
          (>! c (parse-url))))
      (println "Running threads: " workers)
      c)))
