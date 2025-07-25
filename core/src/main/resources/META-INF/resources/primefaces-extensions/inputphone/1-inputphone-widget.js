/**
 * PrimeFaces Extensions InputPhone Widget.
 *
 * @author Jasper de Vries jepsar@gmail.com
 * @since 7.0
 */
PrimeFaces.widget.ExtInputPhone = class extends PrimeFaces.widget.BaseWidget {

    /**
     * Initializes the widget.
     *
     * @param {object}
     *            cfg The widget configuration.
     */
    init(cfg) {
        super.init(cfg);
        this.disabled = cfg.disabled;

        this.bindInputs();
        this.bindComponent();
        this.bindEvents();
    }

    /**
     * @override
     * @inheritdoc
     * @param {PrimeFaces.PartialWidgetCfg<TCfg>} cfg
     */
    refresh(cfg) {
        this._remove();
        super.refresh(cfg);
    }

    /**
     * @override
     * @inheritdoc
     */
    destroy() {
        super.destroy();
        this._remove();
    }

    /**
     * Clean up this widget and remove elements from DOM.
     * @private
     */
    _remove() {
        if (this.iti) {
            this.currentCountry = this.inputIso2Jq.val();
            this.iti.destroy();
            this.iti = null;
        }
    }

    /**
     * Binds the input elements to jQuery objects and sets up metadata.
     *
     * This method initializes jQuery objects for the input elements based on their IDs.
     * It also assigns data for PrimeFaces metadata to the main input element.
     */
    bindInputs() {
        // JQuery inputs
        this.inputJq = $(this.jqId + '_input');
        this.input = this.inputJq[0];
        this.inputIso2Jq = $(this.jqId + '_iso2');
        this.inputHiddenJq = $(this.jqId + '_hidden');

        // pfs metadata
        this.inputJq.data(PrimeFaces.CLIENT_ID_DATA, this.id);

        // now add visual effects to inputs
        this.bindVisualEffects();
    }

    /**
     * Binds and initializes the component.
     *
     * This method sets the initial country configuration if it's defined.
     * It then creates and initializes the international telephone input component.
     */
    bindComponent() {
        // #1623 Initial country can be set back accidentally to original if AJAX validation issue
        if (this.currentCountry) {
            this.cfg.initialCountry = this.currentCountry;
        }

        // if inside dialog we have to attach to dialog
        this.setupDialogSupport();

        // component creation
        this.iti = intlTelInput(this.input, this.cfg);
    }

    /**
     * Renders the disabled state for the input elements.
     * If the component is marked as disabled, it disables the relevant input elements
     * and adds a CSS class to indicate the disabled state.
     */
    bindVisualEffects() {
        // visual effects
        PrimeFaces.skinInput(this.inputJq);

        if (this.disabled) {
            this.inputJq.attr("disabled", "disabled");
            this.inputJq.addClass("ui-state-disabled");
            this.inputIso2Jq.attr("disabled", "disabled");
            this.inputHiddenJq.attr("disabled", "disabled");
        }
    }

    bindEvents() {
        let $this = this;

        this.input.addEventListener('countrychange', function () {
            let country = $this.getCountry();
            let phoneNumber = $this.getNumber();
            $this.inputIso2Jq.val(country.iso2);
            $this.inputHiddenJq.val(phoneNumber);
            if ($this.hasBehavior('countrySelect')) {
                var ext = {
                    params: [{
                        name: $this.id + '_name',
                        value: country.name
                    }, {
                        name: $this.id + '_iso2',
                        value: country.iso2
                    }, {
                        name: $this.id + '_dialCode',
                        value: country.dialCode
                    }]
                };
                $this.callBehavior('countrySelect', ext);
            }
        });

        // get the current attached events if using CSP
        let events = this.inputJq[0] ? $._data(this.inputJq[0], "events") : null;

        // use DOM if non-CSP and JQ event if CSP
        let originalOninput = this.inputJq.prop('oninput');
        if (!originalOninput && events && events.input) {
            originalOninput = events.input[0].handler;
        }

        this.inputJq.prop('oninput', null).off('input').on('input', function (e) {
            let oldVal = $this.inputHiddenJq.val();
            $this.inputHiddenJq.val($this.getNumber());
            if (originalOninput && originalOninput.call(this, e) === false) {
                $this.inputHiddenJq.val(oldVal);
                return false;
            }
        });
    }

    /**
     * Sets up support for using the input phone within an overlay dialog.
     * @private
     */
    setupDialogSupport() {
        const dlg = this.input.closest('.ui-dialog, .ui-sidebar');
        if (dlg) {
            this.cfg.dropdownContainer = dlg;

            const dialog = $(dlg);
            $(this.input).on('open:countrydropdown', function () {
                dialog.find('.iti__dropdown-content').zIndex(PrimeFaces.nextZindex());
            });
        }
    }

    /**
     * Get the current number in the given format.
     */
    getNumber() {
         if (!this.iti) {
            return '';
        }
        const phoneNumber = this.iti.getNumber() || '';
        const extension = this.iti.getExtension();
        return extension ? `${phoneNumber} x${extension}` : phoneNumber;
    }

    /**
     * Insert a number, and update the selected flag accordingly. Note that if
     * formatOnDisplay is enabled, this will attempt to format the number
     * according to the nationalMode option.
     *
     * @param number
     *            The value to set
     */
    setNumber(number) {
        if (this.iti) {
            this.iti.setNumber(number);
        }
    }

    /**
     * Get the country data for the currently selected flag.
     */
    getCountry() {
        return this.iti ? this.iti.getSelectedCountryData() : '';
    }

    /**
     * Change the country selection (e.g. when the user is entering their
     * address).
     *
     * @param country
     *            The country code
     */
    setCountry(country) {
        if (this.iti) {
            this.iti.setCountry(country);
        }
    }

    /**
     * Change the placeholderNumberType option.
     *
     * @param type
     *            the new type like "FIXED_LINE"
     */
    setPlaceholderNumberType(type) {
        if (this.iti) {
            this.iti.setPlaceholderNumberType(type);
        }
    }

    /**
     * Check if the current number is possible.
     *
     * @return true if valid, false if not
     */
    isPossibleNumber() {
        if (this.iti) {
            return this.iti.isValidNumber(false);
        }
    }

    /**
     * Validate the current number.
     *
     * @return true if valid, false if not
     */
    isValidNumber() {
        if (this.iti) {
            return this.iti.isValidNumberPrecise();
        }
    }

    /**
     * Get more information about a validation error. Can look up the error code
     * in utils.js.
     */
    getValidationError() {
        return this.iti ? this.iti.getValidationError() : 0;
    }

    /**
     * Focus the component by focusing on the correct input box.
     */
    focus() {
        this.inputJq.focus();
    }

    /**
     * Enable the input
     */
    enable() {
        PrimeFaces.utils.enableInputWidget(this.inputJq);
        PrimeFaces.utils.enableInputWidget(this.inputIso2Jq);
        PrimeFaces.utils.enableInputWidget(this.inputHiddenJq);
        this.disabled = false;
    }

    /**
     * Disable the input
     */
    disable() {
        PrimeFaces.utils.disableInputWidget(this.inputJq);
        PrimeFaces.utils.disableInputWidget(this.inputIso2Jq);
        PrimeFaces.utils.disableInputWidget(this.inputHiddenJq);
        this.disabled = true;
    }

};