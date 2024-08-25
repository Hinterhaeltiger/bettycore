package dev.cnbetty.core.quests;


import net.kyori.adventure.text.Component;

public class Quest {
    private final String id;
    private final Component name;
    private final Component description;

    private Quest(QuestBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
    }

    public static class QuestBuilder {
        private String id;
        private Component name;
        private Component description;

        public QuestBuilder setID(String id) {
            this.id = id;
            return this;
        }

        public QuestBuilder setName(Component name) {
            this.name = name;
            return this;
        }

        public QuestBuilder setDescription(Component description) {
            this.description = description;
            return this;
        }

        public Quest build() {
            return new Quest(this);
        }
    }
}
