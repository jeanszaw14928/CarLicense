// Live Upload Image Preview Handler
function previewImage(input, previewId, placeholderId) {
    const preview = document.getElementById(previewId);
    const placeholder = document.getElementById(placeholderId);

    if (input.files && input.files[0]) {
        const reader = new FileReader();
        reader.onload = function(e) {
            preview.src = e.target.result;
            preview.style.display = 'block';
            placeholder.style.display = 'none';
        };
        reader.readAsDataURL(input.files[0]);
    }
}

// Reset Form Logic
function resetForm() {
    const form = document.getElementById('carRegistrationForm');
    form.reset();
    form.classList.remove('was-validated');

    // Reset upload previews
    document.getElementById('carPhotoPreview').style.display = 'none';
    document.getElementById('carPhotoPlaceholder').style.display = 'block';
}

// Form Submission Validation Handler
function handleFormSubmit(event) {
    event.preventDefault();
    const form = document.getElementById('carRegistrationForm');

    if (!form.checkValidity()) {
        event.stopPropagation();
        form.classList.add('was-validated');
        return;
    }

    // Show Toast Success Notification
    const toastElem = document.getElementById('successToast');
    const toast = new bootstrap.Toast(toastElem);
    toast.show();

    // Reset state after success feedback
    setTimeout(() => {
        resetForm();
    }, 2000);
}