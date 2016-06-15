
$(document).ready(function() {
    $('#zipCodeForm')
        .formValidation({
            framework: 'bootstrap',
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                postalCode: {
                    validators: {
                        zipCode: {
                            country: function(value, validator, $field) {
                                // Map the country names to the code
                                var map = {
                                    'United States': 'US',
                                    'Austria': 'AT',
                                    'Bulgaria': 'BG',
                                    'Brazil': 'BR',
                                    'Canada': 'CA',
                                    'Czech Republic': 'CZ',
                                    'Denmark': 'DK',
                                    'French': 'FR',
                                    'Germany': 'DE',
                                    'India': 'IN',
                                    'Italy': 'IT',
                                    'Morocco': 'MA',
                                    'Netherlands': 'NL',
                                    'Poland': 'PL',
                                    'Portugal': 'PT',
                                    'Romania': 'RO',
                                    'Russia': 'RU',
                                    'Singapore': 'SG',
                                    'Slovakia': 'SK',
                                    'Spain': 'ES',
                                    'Sweden': 'SE',
                                    'Switzerland': 'CH',
                                    'United Kingdom': 'GB'
                                };

                                // Get the selected country
                                var country = $('#zipCodeForm').find('[name="countrySelectBox"]').val();

                                return (country == '') ? '' : (map[country] || '');
                            },
                            message: 'The value is not valid %s postal code'
                        }
                    }
                }
            }
        })
        // Revalidate postal code when changing the country
        .on('change', '[name="countrySelectBox"]', function(e) {
            $('#zipCodeForm').formValidation('revalidateField', 'postalCode');
        });
});

