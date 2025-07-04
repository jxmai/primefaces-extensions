PrimeFacesExt.widget.MarkText = PrimeFaces.widget.BaseWidget.extend({

    init: function (cfg) {
        this._super(cfg);

        this.element = $(this.jqId);
        this.pattern = this.cfg.pattern;

        this.mark();
    },

    mark: function () {
        if (this.pattern) {
            this.element.mark(this.pattern);
        }
    }
});
